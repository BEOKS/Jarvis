package com.beoks.jarvis

interface Strategy {
    fun execute(): CharPipeline {
        TODO("Not yet implemented")
    }

    val subStrategy: List<Strategy>
}
