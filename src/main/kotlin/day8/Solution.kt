package day8

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day8/input.txt").readLines()
    val trees = buildTreesGrid(lines)
    val count = visibleTreeCount(trees)
    val highestScore = highestScore(trees)
    val edgeCount = trees.size * 2 + trees[0].size * 2 - 4
    println(count + edgeCount)
    println(highestScore)
}

private fun buildTreesGrid(lines: List<String>): List<List<Short>> {
    val result = mutableListOf<MutableList<Short>>()
    for (line in lines) {
        val row = mutableListOf<Short>()
        for (c in line) {
            row.add(c.digitToInt().toShort())
        }
        result.add(row)
    }
    return result.toList()
}

private fun visibleTreeCount(trees: List<List<Short>>): Int {
    var count = 0
    var row = 1
    val rowMax = trees.size
    val colMax = trees[0].size
    while (row < rowMax - 1) {
        var col = 1
        while (col < colMax - 1) {
            if (isAnyDirectionVisible(row, col, trees)) {
                count++
            }
            col++
        }
        row++
    }

    return count
}

private fun isAnyDirectionVisible(row: Int, col: Int, trees: List<List<Short>>): Boolean {
    val h = trees[row][col]
    val rowMax = trees.size
    val colMax = trees[0].size

    var top = row - 1
    while (top >= 0) {
        if (trees[top][col] >= h) break
        top--
    }
    if (top < 0) {
        return true
    }

    var left = col - 1
    while (left >= 0) {
        if (trees[row][left] >= h) break
        left--
    }
    if (left < 0) {
        return true
    }

    var right = col + 1
    while (right < colMax) {
        if (trees[row][right] >= h) break
        right++
    }
    if (right == colMax) {
        return true
    }

    var down = row + 1
    while (down < rowMax) {
        if (trees[down][col] >= h) break
        down++
    }
    if (down == rowMax) {
        return true
    }

    return false
}

private fun highestScore(trees: List<List<Short>>): Int {
    val scoreList = mutableListOf<Int>()
    var row = 1
    val rowMax = trees.size
    val colMax = trees[0].size
    while (row < rowMax - 1) {
        var col = 1
        while (col < colMax - 1) {
            scoreList.add(scenicScore(row, col, trees))
            col++
        }
        row++
    }

    return scoreList.max()
}
private fun scenicScore(row: Int, col: Int, trees: List<List<Short>>): Int {
    val h = trees[row][col]
    val rowMax = trees.size
    val colMax = trees[0].size

    var top = row - 1
    var topDistance = 0
    while (top >= 0) {
        if (trees[top][col] > h) {
            break
        }
        if (trees[top][col] == h) {
            topDistance++
            break
        }
        topDistance++
        top--
    }

    var left = col - 1
    var leftDistance = 0
    while (left >= 0) {
        if (trees[row][left] > h) {
            break
        }
        if (trees[row][left] == h) {
            leftDistance++
            break
        }
        leftDistance++
        left--
    }

    var right = col + 1
    var rightDistance = 0
    while (right < colMax) {
        if (trees[row][right] > h) {
            break
        }
        if (trees[row][right] == h) {
            rightDistance++
            break
        }
        rightDistance++
        right++
    }

    var down = row + 1
    var downDistance = 0
    while (down < rowMax) {
        if (trees[down][col] > h) {
            break
        }
        if (trees[down][col] == h) {
            downDistance++
            break
        }
        downDistance++
        down++
    }

    return topDistance * leftDistance * rightDistance * downDistance
}