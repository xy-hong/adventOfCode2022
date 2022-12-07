package day5

import java.io.File
import java.lang.StringBuilder

fun main() {
    val lines = File("src/day1.day2.main/kotlin/day5/input.txt").readLines()
    val moves = lines.subList(10, lines.size)
    val stacks = listOf(
        ArrayDeque(mutableListOf("D", "L", "J", "R", "V", "G", "F")),
        ArrayDeque(mutableListOf("T", "P", "M", "B", "V", "H", "J", "S")),
        ArrayDeque(mutableListOf("V", "H", "M", "F", "D", "G", "P", "C")),
        ArrayDeque(mutableListOf("M", "D", "P", "N", "G", "Q")),
        ArrayDeque(mutableListOf("J", "L", "H", "N", "F")),
        ArrayDeque(mutableListOf("N", "F", "V", "Q", "D", "G", "T", "Z")),
        ArrayDeque(mutableListOf("F", "D", "B", "L")),
        ArrayDeque(mutableListOf("M", "J", "B", "S", "V", "D", "N")),
        ArrayDeque(mutableListOf("G", "L", "D"))
    )

    println(getTop(moves, stacks))
}

private fun getTop(moves: List<String>, stacks: List<ArrayDeque<String>>): String {
    for (move in moves) {
        val s = move.trim().split(" ")
        move2(s[1].toInt(), s[3].toInt(), s[5].toInt(), stacks)
    }

    var result = StringBuilder()
    for (stack in stacks) {
        result.append(stack.last())
    }
    return result.toString()
}

private fun move(number: Int, from: Int, to: Int, stacks: List<ArrayDeque<String>>) {
    var i = 0
    while (i < number) {
        stacks[to-1].add(stacks[from-1].removeLast())
        i++
    }
}

private fun move2(number: Int, from: Int, to: Int, stacks: List<ArrayDeque<String>>) {
    var i = 0
    val tmp = mutableListOf<String>()
    while (i < number) {
        tmp.add(stacks[from-1].removeLast())
        i++
    }

    var index = tmp.lastIndex
    while (index >= 0) {
        stacks[to-1].add(tmp[index])
        index--
    }
}

