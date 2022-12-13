package ch.jvi.aoc.frame

import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate

fun main() {
    loadData()
}

fun loadDataText(day: Int = LocalDate.now().dayOfMonth, year: Int = LocalDate.now().year): String {
    return loadDataFile(day, year).readText()
}

fun loadData(day: Int = LocalDate.now().dayOfMonth, year: Int = LocalDate.now().year): List<String> {
    return loadDataFile(day, year).readLines()
}

fun loadDataFile(day: Int, year: Int): File {
    val file = File(fileName(day, year))
    if (!file.exists()) {
        return downloadData(day, year)
    }
    return file
}

fun downloadData(day: Int, year: Int): File {
    val file = File(fileName(day, year))
    with(URL(inputURL(day, year)).openConnection() as HttpURLConnection) {
        requestMethod = "POST"
        doOutput = true
        setRequestProperty("Cookie", sessionCookie())
        try {
            inputStream.use { input -> file.outputStream().use { input.copyTo(it) } }
        } catch (e: Exception) {
            println(errorStream)
        }
    }
    return file
}

fun fileName(day: Int, year: Int): String {
    return "src/main/resources/$year/data_$day"
}

fun inputURL(day: Int, year: Int): String {
    return "https://adventofcode.com/$year/day/$day/input"
}

fun sessionCookie(): String {
    return ""
}
