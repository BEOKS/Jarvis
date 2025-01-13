package com.beoks.jarvis

interface KnowledgeGraphRepository {

    // KnowledgeGraph nodes
    val nodes: List<Node>

    fun save(node: Node):Node

    fun append(content: String){
        link(
            save(
                Node(
                    name = content.title(),
                    content = content,
                )
            ), search(content)
        )
    }

    fun link(from: Node, to: List<Node>){
        from.links.addAll(to)
        to.forEach { node -> node.backLinks.add(node) }
    }

    fun save(content: String){
        if(needExtend(content)){
            append(content)
        }
        search(content)
    }

    fun needExtend(content: String): Boolean {
        TODO("Not yet implemented")
    }

    fun search(query: String): List<Node>

    fun String.title(): String

    data class Node(
        val name: String,
        val content: String,
        var links: MutableList<Node>  = mutableListOf<Node>(),
        var backLinks: MutableList<Node> = mutableListOf<Node>()
    )
}
