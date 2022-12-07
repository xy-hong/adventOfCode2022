package day1

import java.io.File

fun main(args: Array<String>) {
    val calories = File("src/main/kotlin/day1/input.txt").readLines()
    print(topThreeTotal(calories))
}

fun topOne(calories: List<String>): Int {
    val elves = mutableListOf<Int>()
    var total = 0
    for (v in calories) {
        if (v.isBlank()) {
            elves.add(total)
            total = 0
            continue
        }

        total += v.toInt()
    }

    return elves.max()
}

fun topThreeTotal(calories: List<String>): Int {
    val elves = mutableListOf<Int>()
    var total = 0
    for (v in calories) {
        if (v.isBlank()) {
            elves.add(total)
            total = 0
            continue
        }

        total += v.toInt()
    }

    return elves.sorted().takeLast(3).sum()
}