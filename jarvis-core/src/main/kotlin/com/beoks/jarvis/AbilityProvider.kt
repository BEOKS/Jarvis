package com.beoks.jarvis

/**
 * AbilityRetriever is a class that retrieves abilities from a given command.
 */
interface AbilityProvider{
    val abilities: List<Ability>

    /**
     * Checks if the ability is available for the command.
     * @param command The command to check.
     * @return True if the ability is available, false otherwise.
     */
    fun Ability.isAbilityAvailable(command: String): Boolean

    /**
     * Retrieves the ability from the command.
     * @param command The command to retrieve the ability from.
     * @return The ability from the command.
     */
    fun getAbility(command: String): List<Ability> = abilities.filter { it.isAbilityAvailable(command) }
}
