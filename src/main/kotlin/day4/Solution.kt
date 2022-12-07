package day4

import java.io.File

fun main() {
    val lines = File("src/day1.day2.main/kotlin/day4/input.txt").readLines()
    var sum = 0
    for (line in lines) {
        line.trim().split(",").also {
            val s = it[0].split("-")
            val range1 = s[0].toInt() to s[1].toInt()
            val s2 = it[1].split("-")
            val range2 = s2[0].toInt() to s2[1].toInt()

            if (isOverlap(range1, range2)) {
                sum += 1
            }
        }
    }

    println(sum)
}

private fun isContains(range1: Pair<Int,Int>, range2: Pair<Int,Int>): Boolean {
    if (range1.first <= range2.first && range1.second >= range2.second) {
        return true
    }

    if (range1.first >= range2.first && range1.second <= range2.second) {
        return true
    }

    return false
}

private fun isOverlap(range1: Pair<Int, Int>, range2: Pair<Int, Int>): Boolean {
    if (range1.second < range2.first || range2.second < range1.first) {
        return false
    }

    return true
}