package com.beoks.jarvis

interface Processor<I, O> {
    val type: String get() = this::class.simpleName ?: "None"
    fun process(input: I): O
}

interface Workflow<I, O> : Processor<I, O>

interface Pipeline<T> : Workflow<T, T> {
    val processor: List<Processor<T, T>>
    override fun process(input: T): T = processor.fold(input) { acc, processor -> processor.process(acc) }
}

interface Router<I, O> : Workflow<I, O> {
    val processor: List<Processor<I, O>>
    val selector: (List<Processor<I, O>>) -> Processor<I, O>
    override fun process(input: I): O = selector(processor).process(input)
}

interface Repeater<I, O> : Workflow<I, O> {
    val processor: Processor<I, O>
    val condition: (O) -> Boolean
    override fun process(input: I): O {
        var output: O;
        do {
            output = processor.process(input)
        } while (condition(output))
        return output
    }
}

interface BatchProcessor<I, O> : Workflow<I, O> {
    val processor: List<Processor<I, O>>
    val divider: (I) -> List<I>
    val aggregator: (List<O>) -> O
    override fun process(input: I): O {
        return divider(input).zip(processor)
            .map { (input, processor) -> processor.process(input) }
            .let { aggregator(it) }
    }
}
