package com.beoks.jarvis

interface Reasoner : Processor<String, String> {
    fun reason(ask: String): String
}
