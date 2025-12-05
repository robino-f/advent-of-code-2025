import java.io.File

class Day5(val ranges: MutableList<MutableList<Long>>, val ingredients: List<Long>) {
    fun solvePart1(): Int {
        return ingredients.filter { ingredient -> ranges.find { it[0] <= ingredient && it[1] >= ingredient } != null }.size
    }

    fun solvePart2(): Long {
        ranges.sortedBy { it[0] }

        val merged = mutableListOf<List<Long>>()
        for ((start, end) in ranges) {
            if (merged.isEmpty()) {
                merged.add(listOf(start,end))
            } else {
                val last = merged.last()
                if (start <= last[1]) {
                    merged[merged.size - 1] = listOf(last[0], maxOf(last[1], end))
                } else {
                    merged.add(listOf(start,end))
                }
            }
        }

        return merged.sumOf { (start, end) -> end - start + 1 }
    }
}

fun main() {
    val input = File("./resources/day-5.txt").readLines()
    var i = 0
    var lastLine = input[0]
    val ranges = mutableListOf<MutableList<Long>>()
    val ingredients = mutableListOf<Long>()

    while (lastLine != "") {
        ranges.add(lastLine.split('-').map { it.toLong() }.toMutableList())
        i++
        lastLine = input[i]
    }
    i++

    while (i < input.size) {
        ingredients.add(input[i].toLong())
        i++
    }

    val day = Day5(ranges, ingredients)

    val part1 = day.solvePart1()
    println("part1: $part1") // 726

    val part2 = day.solvePart2()
    println("part2: $part2") // 354226555270043
}