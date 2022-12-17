package ch.jvi.aoc.`2022`.`08`

import ch.jvi.aoc.frame.loadData

fun main() {
    val lines = loadData(8, 2022)
//    puzzle1(lines)
    puzzle2(lines)
}

fun puzzle1(lines: List<String>) {
    val field = lines.map { it.map { c -> c.digitToInt() } }
    val visibleTrees = mutableSetOf<Pair<Int, Int>>()
    for (i in lines.first().indices) {
        for (j in lines.indices) {
            if (isVisible(i, j, field)) {
                visibleTrees.add(Pair(i, j))
            }
        }
    }
    print(visibleTrees.size)
}

fun isVisible(i: Int, j: Int, field: List<List<Int>>): Boolean {
    val treeHeight = field[i][j]

    return field[i].drop(j + 1).all { it < treeHeight }                             // Right
            || field[i].take(j).all { it < treeHeight }                              // Left
            || (0 until i).all { field[it][j] < treeHeight }                    // Up
            || (i + 1 until field.size).all { field[it][j] < treeHeight }      // Down
}

fun puzzle2(lines: List<String>) {

}
