package com.beoks.jarvis

class Jarvis(
    private val commandProcessor : CommandProcessor,
    private val searchEngines : SearchEngine,
    private val reasoner: Reasoner<String,String>,
    private val booleanReasoner: Reasoner<String,Boolean>
){

    fun answer(ask : String): String{
        val stringBuilder = StringBuilder(ask)
        if(ask.needSearchEngine()){
            stringBuilder.append("###Reference: ${searchEngines.search(ask)}")
        }
        if(ask.isCommand()){
            stringBuilder.append("###Command Result: ${commandProcessor.process(ask)}")
        }
        return reasoner.reason(stringBuilder.toString())
    }

    private fun String.isCommand(): Boolean {
        return booleanReasoner.reason("Is this is command? ask = $this")
    }


    private fun String.needSearchEngine(): Boolean {
        return booleanReasoner.reason("IS this is need search more information? ask = $this")
    }
}