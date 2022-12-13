package ch.jvi.aoc.`2022`.`06`

import ch.jvi.aoc.frame.loadDataText

fun main() {
    val line = loadDataText(6, 2022)

    val p1 = line.windowed(4).indexOfFirst(String::hasUniqueChars)
    println(p1+4)

    val p2 = line.windowed(14).indexOfFirst(String::hasUniqueChars)
    println(p2+14)
}

fun String.hasUniqueChars(): Boolean = all(hashSetOf<Char>()::add)

