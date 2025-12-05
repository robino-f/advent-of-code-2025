import java.io.File

class Day5(val ranges: MutableList<MutableList<Long>>, val ingredients: List<Long>) {
    fun solvePart1(): Int {
        return ingredients.filter { ingredient -> ranges.find { it[0] <= ingredient && it[1] >= ingredient } != null }.size
    }

    fun solvePart2(): Long {
        val sorted = ranges.map { it[0] to it[1] }.sortedBy { it.first }
        val merged = mutableListOf<Pair<Long, Long>>()

        for ((start, end) in sorted) {
            if (merged.isEmpty()) {
                merged.add(start to end)
            } else {
                val last = merged.last()
                if (start <= last.second) {
                    merged[merged.size - 1] = last.first to maxOf(last.second, end)
                } else {
                    merged.add(start to end)
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
