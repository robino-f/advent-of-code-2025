import java.io.File

class Day3(val batteries: List<String>) {
    fun solvePart1(numberOfDigits: Int = 2): Long {
        var maximumJoltage = 0.toLong()
        for (battery in batteries) {
            var joltage = ""
            var max = '0'
            var maxIndex = 0

            for (shift in 0..<numberOfDigits) {
                for (i in maxIndex..(battery.length - numberOfDigits + shift)) {
                    if (battery[i] > max) {
                        max = battery[i]
                        maxIndex = i + 1

                    }
                }
                joltage += max
                max = '0'
            }
            maximumJoltage += joltage.toLong()
        }
        return maximumJoltage
    }

    fun solvePart2(): Long {
        return solvePart1(12)
    }
}

fun main() {
    val input = File("./resources/day-3.txt").readLines()
    val day = Day3(input)

    val part1 = day.solvePart1()
    println("part1: $part1") // 17087

    val part2 = day.solvePart2()
    println("part2: $part2") // 169019504359949
}