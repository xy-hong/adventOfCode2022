package day3

import java.io.File


fun main() {
    val lines = File("src/day1.day2.main/kotlin/day3/input.txt").readLines()
    println(part2(lines))
}

private fun part1(lines: List<String>): Int {
    var sum = 0
    for (line in lines) {
        val length = line.length
        val firstPart = line.substring(0, length/2).toList()
        val secondPart = line.substring(length/2).toList()
        val share = firstPart intersect secondPart
        sum += calPriority(share.first())
    }

    return sum
}

private fun part2(lines: List<String>): Int {
    var sum = 0
    val length = lines.size
    var i = 0
    while (i<length) {
        val item = lines[i].toList() intersect lines[i+1].toList().toSet() intersect lines[i+2].toList().toSet()
        sum += calPriority(item.first())
        i += 3
    }
    return sum
}

private fun calPriority(c: Char): Int {
    if (c.isLowerCase()) {
        return c - 'a' + 1
    }

    if (c.isUpperCase()) {
        return c - 'A' + 26 + 1
    }

    throw RuntimeException()
}