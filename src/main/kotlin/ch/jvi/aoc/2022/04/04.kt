import ch.jvi.aoc.frame.loadData
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.util.function.Consumer
import java.util.function.Function

fun main() {
    val lines = loadData(4, 2022)
    puzzle1(lines)
    puzzle2(lines)
}

private fun puzzle1(lines: List<String>) {
    val result1 = lines
        .map { it.split(",") }
        .map { it.flatMap { it.split("-").map { it.toInt() } } }
        .filter { (it[0] <= it[2] && it[1] >= it[3]) || it[0] >= it[2] && it[1] <= it[3] }
        .size
    println(result1)
}

private fun puzzle2(lines: List<String>) {
    val result2 = lines
        .map { it.split(",") }
        .map { it.flatMap { it.split("-").map { it.toInt() } } }
        .filter { IntRange(it[0], it[1]).intersect(IntRange(it[2], it[3])).isNotEmpty() }
        .size
    println(result2)
}
