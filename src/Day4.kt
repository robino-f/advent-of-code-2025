import java.io.File

class Day4(val lines: List<MutableList<String>>) {
    fun solvePart1(markRolls: Boolean = false): Int {
        var rolls = 0

        for (y in 0..<lines.size) {
            for (x in 0..<lines[y].size) {
                if (lines[y][x] != "@") continue

                val positionsToCheck = mutableListOf<List<Int>>()

                val hasColumnBefore = x > 0
                if (hasColumnBefore) {
                    positionsToCheck.add(listOf(x - 1, y))
                }

                val hasColumnAfter = x < lines[y].size - 1
                if (hasColumnAfter) {
                    positionsToCheck.add(listOf(x + 1, y))
                }

                val hasRowBefore = y > 0
                if (hasRowBefore) {
                    positionsToCheck.add(listOf(x, y - 1))
                }

                val hasRowAfter = y < lines.size - 1
                if (hasRowAfter) {
                    positionsToCheck.add(listOf(x, y + 1))
                }

                if (hasColumnBefore && hasRowBefore) {
                    positionsToCheck.add(listOf(x - 1, y - 1))
                }
                if (hasColumnBefore && hasRowAfter) {
                    positionsToCheck.add(listOf(x - 1, y + 1))
                }
                if (hasColumnAfter && hasRowBefore) {
                    positionsToCheck.add(listOf(x + 1, y - 1))
                }
                if (hasColumnAfter && hasRowAfter) {
                    positionsToCheck.add(listOf(x + 1, y + 1))
                }

                val adjacentRolls = positionsToCheck.filter { lines[it[1]][it[0]] == "@" }.size
                if (adjacentRolls < 4) {
                    rolls++
                    if (markRolls) {
                        lines[y][x] = "x"
                    }
                }
            }
        }
        return rolls
    }

    fun solvePart2(): Int {
        var previousRolls = 0
        var rolls = 0

        do {
            previousRolls = rolls
            rolls += solvePart1(true)
        } while (previousRolls != rolls)

        return rolls
    }
}

fun main() {
    val input = File("./resources/day-4.txt").readLines().map { it.split("").toMutableList() }
    val day = Day4(input)

    val part1 = day.solvePart1()
    println("part1: $part1") // 1351

    val part2 = day.solvePart2()
    println("part2: $part2") // 8345
}