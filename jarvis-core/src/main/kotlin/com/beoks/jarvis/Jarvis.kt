package com.beoks.jarvis

class Jarvis(
    private val strategist: Strategist,
    private val abilityProvider: AbilityProvider,
) : Strategist by strategist,
    AbilityProvider by abilityProvider {

    fun execute(command: String): CharSequence {
        TODO("Not yet implemented")
    }
}
