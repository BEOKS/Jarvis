package com.beoks.jarvis

class Jarvis(
    private val reasoner: Reasoner,
    private val strategist: Strategist,
    private val abilityProvider: AbilityProvider,
) : Reasoner by reasoner,
    Strategist by strategist,
    AbilityProvider by abilityProvider {

    fun execute(command: String): CharSequence {
        return ""
    }
}
