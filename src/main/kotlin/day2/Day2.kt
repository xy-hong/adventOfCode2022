package day2

import java.io.File

private val winRules = setOf(
    "Y" to "A",
    "Z" to "B",
    "X" to "C"
)

private val loseRules = setOf(
    "Z" to "A",
    "X" to "B",
    "Y" to "C"
)

private val drawRules = setOf(
    "X" to "A",
    "Y" to "B",
    "Z" to "C"
)

private val selectedMap = mapOf(
    ("A" to "X") to 3, // C
    ("A" to "Y") to 1, // A
    ("A" to "Z") to 2, // B
    ("B" to "X") to 1, // A
    ("B" to "Y") to 2, // B
    ("B" to "Z") to 3, // C
    ("C" to "X") to 2, // B
    ("C" to "Y") to 3, // C
    ("C" to "Z") to 1, // A
)

fun main() {
    val lines = File("src/main/kotlin/day2/input.txt").readLines()
    val rounds = getRounds(lines)
    var total = 0
    for (round in rounds) {
        total += calScore2(round.second, round.first)
    }
    println(total)
}

private fun getRounds(lines: List<String>): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    for (line in lines) {
        val words = line.trim().split(" ")
        result.add(words[0] to words[1])
    }

    return result
}

private fun calScore(me: String, opponent: String): Int {
    val selectd = when (me) {
        "X" -> 1
        "Y" -> 2
        "Z" -> 3
        else -> throw RuntimeException()
    }

    val round = me to opponent
    val score = when (round) {
        in winRules -> 6
        in loseRules -> 0
        in drawRules -> 3
        else -> throw RuntimeException()
    }

    return score + selectd
}

private fun calScore2(second: String, first: String): Int {
    val r = when (second) {
        "X" -> 0
        "Y" -> 3
        "Z" -> 6
        else -> throw RuntimeException()
    }

    val selectedScore = selectedMap[first to second]

    return r + selectedScore!!
}