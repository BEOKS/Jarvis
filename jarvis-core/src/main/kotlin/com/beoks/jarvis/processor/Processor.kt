package com.beoks.jarvis.processor

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface Processor<I, O> {
    suspend fun process(input: I): O
}

interface Workflow<I, O> : Processor<I, O>

interface Pipeline<T> : Workflow<T, T> {
    val processors: List<Processor<T, T>>

    override suspend fun process(input: T): T = processors.fold(input) { acc, processor ->
        processor.process(acc)
    }
}

typealias Selector<I, O> = suspend (List<Processor<I, O>>) -> Processor<I, O>

interface Router<I, O> : Workflow<I, O> {
    val processors: List<Processor<I, O>>
    val selector: Selector<I, O>

    override suspend fun process(input: I): O = selector(processors).process(input)
}

typealias Condition<O> = suspend (O) -> Boolean

interface Repeater<I, O> : Workflow<I, O> {
    val processor: Processor<I, O>
    val condition: Condition<O>

    override suspend fun process(input: I): O {
        var output: O
        do {
            output = processor.process(input)
        } while (condition(output))
        return output
    }
}

typealias Divider<I> = suspend (I) -> List<I>
typealias Aggregator<O> = suspend (List<O>) -> O

interface BatchProcessor<I, O> : Workflow<I, O> {
    val processors: List<Processor<I, O>>
    val divider: Divider<I>
    val aggregator: Aggregator<O>

    override suspend fun process(input: I): O = coroutineScope {
        val inputs = divider(input)
        val deferredResults = inputs.zip(processors).map { (input, processor) ->
            async { processor.process(input) }
        }
        val outputs = deferredResults.awaitAll()
        aggregator(outputs)
    }
}
