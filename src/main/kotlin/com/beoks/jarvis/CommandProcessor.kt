package com.beoks.jarvis

interface CommandProcessor {
    fun process(command: String): String
}
