package com.beoks.jarvis

import GabiaProxyOpenAiTextGenerator
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class GabiaProxyOpenAiTextGeneratorTest {

    @Test
    fun generate() {
        val textGenerator = GabiaProxyOpenAiTextGenerator()
        val result = textGenerator.generate("What is the meaning of life?")
        println("result = $result")
        assertNotEquals(result, "")
    }
}
