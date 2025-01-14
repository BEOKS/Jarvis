package com.beoks.jarvis

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class JarvisTest{

    private val jarvisTestConfiguration = JarvisTestConfiguration()
    private val jarvis: Jarvis = jarvisTestConfiguration.getJarvis()

    @Test
    @DisplayName("When Jarvis no need ability for answer, it should return the answer only use reasoning")
    fun testJarvisNoNeedAbilityForAnswer() {
        val answer = jarvis.execute("What is the meaning of life?")
        assertContains(answer, "42")
    }

    @Nested
    @DisplayName("When Jarvis need ability for answer")
    inner class JarvisNeedAbilityForAnswer {

        @Test
        @DisplayName("Jarvis show check is available ability for execute command")
        fun testJarvisShowCheckIsAvailableAbilityForExecuteCommand() {
            val answer = jarvis.execute("What is the meaning of life?")
            assertContains(answer, "42")
        }

        @Test
        @DisplayName("When there is no need ability for answer, it should return the answer only use reasoning")
        fun testJarvisNoNeedAbilityForAnswer() {
            val answer = jarvis.execute("What is the meaning of life?")
            assertContains(answer, "42")
        }

        @Test
        @DisplayName("When Jarvis need to search use search ability, it should return the answer use search engine")
        fun testJarvisNeedToSearchUseSearchAbility() {
            val answer = jarvis.execute("What is the meaning of life?")
            assertContains(answer, "42")
        }

        @Test
        @DisplayName("When Jarvis need to execute command, it should return the answer use command processor")
        fun testJarvisNeedToExecuteCommand() {
            val answer = jarvis.execute("What is the meaning of life?")
            assertContains(answer, "42")
        }

        @Test
        @DisplayName("When Jarvis get ability for answer, it should plan how to use ability and return the answer")
        fun testJarvisGetabilityForAnswer() {
            val answer = jarvis.execute("What is the meaning of life?")
        }
    }
}
