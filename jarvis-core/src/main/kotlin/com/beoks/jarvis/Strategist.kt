package com.beoks.jarvis

/**
 * Strategist is a class that represents a strategy for solving a problem.
 * It defines the steps and approaches to find a solution to a given problem with a set of abilities.
 */
interface Strategist {
    fun plan(abilities: List<Ability>, command: String): Strategy {
        TODO("Not yet implemented")
    }
}
