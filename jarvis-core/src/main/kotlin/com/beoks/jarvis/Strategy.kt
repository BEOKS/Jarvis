package com.beoks.jarvis

interface Strategy {
    fun execute(): CharSequence {
        TODO("Not yet implemented")
    }

    val subStrategy: List<Strategy>
}
