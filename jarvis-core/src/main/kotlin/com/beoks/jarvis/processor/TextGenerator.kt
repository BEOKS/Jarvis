package com.beoks.jarvis.processor

interface TextGenerator : Processor<String, String> {
    suspend fun generate(ask: String): String
}
