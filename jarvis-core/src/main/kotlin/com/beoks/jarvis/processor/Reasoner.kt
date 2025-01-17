package com.beoks.jarvis.processor

interface Reasoner : Processor<String, String> {
    fun reason(ask: String): String
}
