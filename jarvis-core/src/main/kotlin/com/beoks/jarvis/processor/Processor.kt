package com.beoks.jarvis.processor

interface Processor<I, O> {
    fun process(input: I): O
}

interface Workflow<I, O> : Processor<I, O>

interface Pipeline<T> : Workflow<T, T> {
    val processor: List<Processor<T, T>>
    override fun process(input: T): T = processor.fold(input) { acc, processor -> processor.process(acc) }
}

typealias Selector<I, O> = (List<Processor<I, O>>) -> Processor<I, O>

interface Router<I, O> : Workflow<I, O> {
    val processor: List<Processor<I, O>>
    val selector: Selector<I, O>
    override fun process(input: I): O = selector(processor).process(input)
}

typealias Condition<O> = (O) -> Boolean

interface Repeater<I, O> : Workflow<I, O> {
    val processor: Processor<I, O>
    val condition: Condition<O>
    override fun process(input: I): O {
        var output: O;
        do {
            output = processor.process(input)
        } while (condition(output))
        return output
    }
}

typealias Divider<I> = (I) -> List<I>
typealias Aggregator<O> = (List<O>) -> O

interface BatchProcessor<I, O> : Workflow<I, O> {
    val processor: List<Processor<I, O>>
    val divider: Divider<I>
    val aggregator: Aggregator<O>
    override fun process(input: I): O {
        return divider(input).zip(processor)
            .map { (input, processor) -> processor.process(input) }
            .let { aggregator(it) }
    }
}
