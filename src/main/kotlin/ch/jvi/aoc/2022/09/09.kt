package ch.jvi.aoc.`2022`.`09`

import ch.jvi.aoc.frame.loadData
import kotlin.math.abs

fun main() {
    val lines = loadData(9, 2022)
    puzzle1(lines)
    puzzle2(lines)
}

fun puzzle1(lines: List<String>) {
    val instructions = lines.map { it.split(" ") }
    val results = mutableSetOf<Position>()

    var H = Position(0, 0)
    var T = Position(0, 0)

    results.add(T)
    for (instruction in instructions) {
        val n = instruction[1].toInt()
        val d = instruction[0]

        for (i in 1..n) {
            H = moveHead(H, d)
            if (!isTouching(H, T)) {
                T = pullTail(H, d)
                results.add(T)
            }
        }
    }
    println(results.size)
}

fun puzzle2(lines: List<String>) {
    // WIP
    val results = mutableSetOf<Position>()
    val instructions = lines.map { it.split(" ") }
    val totallyNoSnake = (1..10).map { Position(0, 0) }.toMutableList()

    results.add(totallyNoSnake.last())

    for (instruction in instructions) {
        val n = instruction[1].toInt()
        val d = instruction[0]

        for (i in 1..n) {
            totallyNoSnake[0] = moveHead(totallyNoSnake[0], d)
            for (j in 1..totallyNoSnake.size-1) {
                if (!isTouching(totallyNoSnake[j-1], totallyNoSnake[j])) {
                    totallyNoSnake[j] = pullTail(totallyNoSnake[j-1], d)
                }
            }
            results.add(totallyNoSnake.last())
        }
    }
    println(results.size)
}

fun isTouching(first: Position, second: Position): Boolean {
    return abs(first.x - second.x) <= 1 && abs(first.y - second.y) <= 1
}

fun moveHead(p: Position, d: String): Position = when (d) {
    "R" -> Position(p.x+1, p.y)
    "L" -> Position(p.x-1, p.y)
    "U" -> Position(p.x, p.y+1)
    "D" -> Position(p.x, p.y-1)
    else -> p
}

fun pullTail(p: Position, d: String): Position = when (d) {
    "R" -> moveHead(p, "L")
    "L" -> moveHead(p, "R")
    "U" -> moveHead(p, "D")
    "D" -> moveHead(p, "U")
    else -> p
}

data class Position(val x: Int, val y: Int)
