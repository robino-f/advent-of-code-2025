import java.io.File

class Day2(val ranges: List<String>) {
    fun solvePart1(): Long {
        var invalidIdsSum = 0.toLong()
        for (range in ranges) {
            val values = range.split('-').map(String::toLong)
            for (i in values[0]..values[1]) {
                val id = i.toString()
                if (id.length % 2 != 0) continue

                val firstPart = id.slice(0..<id.length / 2)
                val secondPart = id.slice(id.length / 2..<id.length)

                if (firstPart == secondPart) {
                    invalidIdsSum += i
                }
            }
        }
        return invalidIdsSum
    }

    fun solvePart2(): Long {
        var invalidIdsSum = 0.toLong()
        for (range in ranges) {
            val values = range.split('-').map(String::toLong)
            for (i in values[0]..values[1]) {
                val id = i.toString()

                if(id.matches(Regex("^(\\d+)\\1+$"))){
                    invalidIdsSum += i
                }
            }
        }
        return invalidIdsSum
    }
}

fun main() {
    val input = File("./resources/day-2.txt").readLines().joinToString(separator = "")
    var ranges = input.split(',')
    val day = Day2(ranges)

    val part1 = day.solvePart1()
    println("part1: $part1") // 13919717792

    val part2 = day.solvePart2()
    println("part2: $part2") // 14582313461
}