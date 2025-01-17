package com.beoks.jarvis

interface TextGenerator : Processor<String, String> {
    suspend fun generate(ask: String): String
}
