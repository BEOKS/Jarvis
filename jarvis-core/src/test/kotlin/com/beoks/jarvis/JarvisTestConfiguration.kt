package com.beoks.jarvis

class JarvisTestConfiguration {
    fun getJarvis(): Jarvis {
        return Jarvis(
            getReasoner(),
            getStrategist(),
            getAbilityRetriever()
        )
    }

    private fun getReasoner(): Reasoner {
        TODO("Not yet implemented")
    }

    private fun getAbilityRetriever(): AbilityProvider {
        TODO("Not yet implemented")
    }

    fun getStrategist(): Strategist {
        TODO("Not yet implemented")
    }

}
