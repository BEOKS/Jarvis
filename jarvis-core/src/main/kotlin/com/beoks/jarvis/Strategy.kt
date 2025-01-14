package com.beoks.jarvis

interface Strategy {
    val subStrategy: List<Strategy>
}
