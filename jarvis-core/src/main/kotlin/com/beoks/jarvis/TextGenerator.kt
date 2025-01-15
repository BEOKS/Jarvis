package com.beoks.jarvis

interface TextGenerator {
    suspend fun generate(ask: String): String
}
