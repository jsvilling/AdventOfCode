package ch.jvi.aoc.`2022`.`07`

import ch.jvi.aoc.frame.loadData
import java.util.InputMismatchException
import java.util.function.Predicate
import kotlin.math.abs

fun main() {
    val lines = loadData(7, 2022)
    puzzle1(lines)
    puzzle2(lines)
}

fun puzzle1(lines: List<String>) {
    val rootNode = parseFileSystem(lines)
    println(rootNode.sumDirsBelow(100000))
}

fun puzzle2(lines: List<String>) {
    val rootNode = parseFileSystem(lines)
    val target = abs(70000000 - 30000000 - rootNode.size)
    var result = rootNode.filter { it.size >= target }.minBy { it.size }
    print(result.size)
}

fun parseFileSystem(lines: List<String>): Node {
    val rootNode = Node("/", 0, mutableListOf(), null, true)
    var currentNode = rootNode
    var i = 0
    while (i < lines.size) {
        var line = lines[i]
        if (line[0] == '$') {
            val args = line.split(" ")
            if (args[1] == "cd") {
                val newNode = cd(args[2], currentNode)
                if (args[2] != ".." && args[2] != "/") {
                    currentNode.addChild(Node(args[2], 0, mutableListOf(), currentNode, true))
                }
                currentNode = newNode
            } else if (args[1] == "ls") {
                while (i + 1 < lines.size && lines[i + 1][0] != '$') {
                    i++
                    line = lines[i]
                    val name: String = line.split(" ")[1]
                    val size: Int = line.split(" ")[0].toIntOrNull() ?: 0
                    val isDirectory = line.split(" ")[0] == "dir"
                    currentNode.addChild(Node(name, size, mutableListOf(), currentNode, isDirectory))
                }
            }
        }
        i++
    }
    return rootNode
}

fun cd(path: String, currentNode: Node): Node {
    var result = currentNode
    if (path == "/") {
        while (currentNode.parent != null) {
            result = currentNode.parent
        }
        return result
    }
    if (path == "..") {
        return currentNode.parent ?: currentNode
    }
    return currentNode.children.find { it.name == path } ?: currentNode
}

class Node(
    val name: String,
    var size: Int,
    val children: MutableList<Node>,
    val parent: Node? = null,
    val isDirectory: Boolean = false
) {

    fun print(offset: Int = 0) {
        val type = if (isDirectory) {
            "directory"
        } else {
            "file"
        }
        repeat(offset) { print(" ") }
        println("- $name ($type - $size)")
        children.forEach { it.print(offset + 1) }
    }

    fun addChild(node: Node) {
        if (children.firstOrNull { it.name == node.name } == null) {
            children.add(node)
            findRoot().calcSize()
        }
    }

    fun calcSize(): Int {
        if (isDirectory) {
            this.size = 0
        }
        size += children.sumOf { it.calcSize() }
        return size
    }

    fun findRoot(): Node {
        var node: Node = this
        while (node.parent != null) {
            node = node.parent ?: node
        }
        return node
    }

    fun sumDirsBelow(threshold: Int): Int {
        val childSize = children.filter { it.isDirectory }.sumOf { it.sumDirsBelow(threshold) }
        if (size < threshold) {
            return size + childSize
        }
        return childSize
    }

    fun filter(pred: Predicate<Node>): List<Node> {
        if (pred.test(this)) {
            return mutableListOf(this) + children.flatMap { it.filter(pred) }
        }
        return children.flatMap { it.filter(pred) }
    }

}
