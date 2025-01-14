package com.beoks.jarvis

class JarvisTestConfiguration {
    fun getJarvis(): Jarvis {
        return Jarvis(
            getStrategist(),
            getAbilityRetriever()
        )
    }

    private fun getAbilityRetriever(): AbilityProvider {
        TODO("Not yet implemented")
    }

    fun getStrategist(): Strategist {
        TODO("Not yet implemented")
    }

}
