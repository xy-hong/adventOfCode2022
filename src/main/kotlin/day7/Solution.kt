package day7

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/day7/input.txt").readLines()
    val root = buildFilesTree(lines.subList(1, lines.size))
    val dirList = mutableListOf<Long>()
    val allDirList = mutableListOf<Long>()
    root!!.size = calSize(root, dirList, allDirList)
    // part one answer
    println(dirList.sum())

    val unusedSize = 70_000_000 - root.size
    val needSize = 30_000_000 - unusedSize

    val answer = allDirList.sorted().first { it > needSize }
    println(answer)
}

private fun buildFilesTree(lines: List<String>): Node? {
    if (lines.isEmpty()) {
        return null
    }

    val root = Node("/", -1, true, null)
    var current = root

    for (line in lines) {
        if (line.startsWith("$ cd")) {
            current = cd(line, current)
        } else if (line.startsWith("$ ls")) {
            continue
        } else if (line.startsWith("dir")) {
            val name = line.split(" ")[1]
            current.children.add(Node(name, -1, true, current))
        } else {
            val size = line.split(" ")[0].toLong()
            val name = line.split(" ")[1]
            current.children.add(Node(name, size, false, current))
        }
    }

    return root
}

private fun cd(line: String, current: Node): Node {
    val path = line.split(" ")[2]
    if (path == ".."){
        return current.parent!!
    }

    return current.children.find { it.name == path }!!
}

private fun calSize(root: Node, dirList: MutableList<Long>, allDirList: MutableList<Long>): Long {
    if (root.isDirectory == false) {
        return root.size
    }

    var size = 0L
    for (node in root.children) {
        size += calSize(node, dirList, allDirList)
    }

    if (size <= 100_000) {
        dirList.add(size)
    }

    allDirList.add(size)
    return size
}

class Node(
    val name: String,
    var size: Long,
    val isDirectory: Boolean,
    val parent: Node?,
    val children: MutableList<Node> = mutableListOf()
)