import ch.jvi.aoc.frame.loadData
import java.util.*
import java.util.function.Consumer

fun main() {
    val lines = loadData(5, 2022)
    puzzle1(lines.toMutableList())
    puzzle2(lines.toMutableList())
}

private fun puzzle1(lines: List<String>) {
    val numberOfStacks = (lines[0].length + 1) / 4
    val stacks = (0 until numberOfStacks).map { mutableListOf<String>() }

    lines
        .takeWhile { it[1] != '1' }
        .map { it.chunked(4) }
        .forEach { row ->  row.forEachIndexed { j, it -> if (it[0] == '[') stacks[j].add(it) }}

    lines
        .dropWhile { it.isEmpty() || it[0] != 'm' }
        .map { it.split(" ") }
        .map { Triple(it[1].toInt() - 1, it[3].toInt() - 1, it[5].toInt() - 1) }
        .forEach { (0..it.first).forEach { i -> stacks[it.third].add(0, stacks[it.second].removeFirst()) } }

    val result = stacks
        .filter { it.size > 0 }
        .map { it.first()[1].toString() }
        .reduce { a, b -> "$a$b" }

    println(result)
}

private fun puzzle2(lines: List<String>) {
    val numberOfStacks = (lines[0].length + 1) / 4
    val stacks = (0 until numberOfStacks).map { mutableListOf<String>() }

    lines
        .takeWhile { it[1] != '1' }
        .map { it.chunked(4) }
        .forEach { row ->  row.forEachIndexed { j, it -> if (it[0] == '[') stacks[j].add(it) }}

    lines
        .dropWhile { it.isEmpty() || it[0] != 'm' }
        .map { it.split(" ") }
        .map { Triple(it[1].toInt()-1, it[3].toInt() - 1, it[5].toInt() - 1) }
        .forEach { trip -> (0..trip.first).forEach { stacks[trip.third].add(it, stacks[trip.second].removeFirst()) } }

    val result = stacks
        .filter { it.size > 0 }
        .map { it.first()[1].toString() }
        .reduce { a, b -> "$a$b" }

    println(result)
}

private fun run(lines: List<String>, transformer: Consumer<Triple<Int, Int, Int>>) {
    val numberOfStacks = (lines[0].length + 1) / 4
    val stacks = (0 until numberOfStacks).map { mutableListOf<String>() }

    lines
        .takeWhile { it[1] != '1' }
        .map { it.chunked(4) }
        .forEach { row ->  row.forEachIndexed { j, it -> if (it[0] == '[') stacks[j].add(it) }}

    lines
        .dropWhile { it.isEmpty() || it[0] != 'm' }
        .map { it.split(" ") }
        .map { Triple(it[1].toInt()-1, it[3].toInt() - 1, it[5].toInt() - 1) }
        .forEach(transformer)

    val result = stacks
        .filter { it.size > 0 }
        .map { it.first()[1].toString() }
        .reduce { a, b -> "$a$b" }

    println(result)
}
