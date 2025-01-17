package com.beoks.jarvis.workflow

/**
 * Workflow is a step-by-step process to accomplish a goal.
 * It composes multiple abilities or other workflows.
 */
interface Workflow {
    val name: String
    fun execute(input: Any? = null): Any?
}


/**
 * Pipeline is a workflow that Jarvis uses to execute a Pipeline of abilities in order.
 */
interface Pipeline : Workflow {
    val steps: List<Workflow>
}

/**
 * Router is a workflow that Jarvis uses to route a request to the appropriate ability.
 */
interface Router : Workflow {
    fun route(input: Any): Workflow
}

/**
 * Parallelization is a workflow that Jarvis uses to orchestrate multiple workers to accomplish a goal.
 */
interface Parallel : Workflow {
    val workers: List<Workflow>
}

/**
 * Loop is a workflow that Jarvis uses to loop a Pipeline of abilities until a condition is met.
 */
interface Loop : Workflow {
    val condition: () -> Boolean
    val loopedWorkflow: Workflow
}
