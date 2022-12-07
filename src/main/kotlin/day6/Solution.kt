package day6

import java.io.File

fun main() {
    val line = File("src/day1.day2.main/kotlin/day6/input.txt").readLines().first()
    var left = 0
    var right = 13

    while (right < line.length) {
        val packet = line.subSequence(left, right+1).toSet()
        if (packet.size == 14) {
            break
        }

        left++
        right++
    }

    println(right + 1)
}


