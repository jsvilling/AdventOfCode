import ch.jvi.aoc.frame.loadData
import java.io.File

fun main() {
    val lines: List<String> = loadData(3, 2022)

    var i = 0
    var sum = 0
    while (i < lines.size) {
        sum += find2(lines.subList(i, i + 3))
        i += 3
    }
    println(sum)
}

fun find2(lines: List<String>): Int {
    val result = CharArray(lines.size)
    result[0] = lines[0][0]
    var c = 0;
    while (!matching(result)) {

        result[0] = lines[0][c]

        for (i in 1..result.size - 1) {
            var d = 0
            while (d < lines[i].length && result[0] != result[i]) {
                result[i] = lines[i][d]
                d++
            }
        }

        c++
    }

    val a = result[0]
    if (a.code < 97) {
        return a.code - 38
    }
    return a.code - 96

}

fun matching(values: CharArray): Boolean {
    return values.size > 0 && values.filter { it.equals(values[0]) }.count() == values.size
}

fun find(line: String): Int {
    var l = line.length - 1
    var m = l / 2
    var c = 0

    var a = line[c]
    var b = line[l]

    while (l >= m && a != b) {
        while(c <= m && a != b) {
            a = line[c]
            b = line[l]
            c++
        }
        c = 0;
        l--
    }


    if (a.code < 97) {
        return a.code - 38
    }
    return a.code - 96

}


