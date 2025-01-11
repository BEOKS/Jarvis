package com.beoks.jarvis

interface Reasoner<T,R> {
    fun reason(ask : T): R
}
