package ch.jvi.aoc.`2022`.`01`

import ch.jvi.aoc.frame.loadData
import kotlin.math.max

fun main() {
    puzzle1()
    puzzle2()
}

fun puzzle1() {
    val lines = loadData(1,  2022)
    var i = 0
    var c = 0;
    var maxC = 0;
    while (i < lines.size) {
        val l = lines[i]
        if (l.isEmpty()) {
            maxC = max(c, maxC)
            c = 0
        } else {
            c += lines[i].toInt()
        }
        i++
    }
    println(maxC)
}

fun puzzle2() {
    val lines = loadData(1,  2022)
    val result = next(Triple(lines, 0, 0))
    println(result.third)
}

fun next(context: Triple<List<String>, Int, Int>): Triple<List<String>, Int, Int> {
    if (context.first.isEmpty()) {
        return context
    }
    if (context.first[0].isNullOrEmpty()) {
        return next(Triple(context.first.subList(1, context.first.size), 0, max(context.second, context.third)))
    }
    return next(Triple(context.first.subList(1, context.first.size), context.second + context.first[0].toInt(), context.third))
}
