import java.io.File

class Day1(val lines: List<String>) {
    fun solvePart1(): Int {
        var numberOfZeros = 0
        var pointerPosition = 50
        for (line in lines) {
            var factor = 1
            if (line[0] == 'L') {
                factor = -1
            }
            val value = line.slice(1..<line.length).toInt()
            pointerPosition += factor * value
            pointerPosition %= 100
            if (pointerPosition < 0) {
                pointerPosition += 100
            }

            if (pointerPosition == 0) {
                numberOfZeros++
            }
        }

        return numberOfZeros
    }

    fun solvePart2(): Int {
        var numberOfZeros = 0
        var pointerPosition = 50
        var lastFactor = 1
        for (line in lines) {
            var factor = 1
            if (line[0] == 'L') {
                factor = -1
            }
            if (lastFactor != factor){
                // direction changed, pick symmetrical position
                pointerPosition = (100 - pointerPosition) % 100
                lastFactor = factor
            }

            val value = line.slice(1..<line.length).toInt()
            pointerPosition += value

            numberOfZeros += pointerPosition / 100
            pointerPosition %= 100
        }

        return numberOfZeros
    }
}

fun main() {
    val input = File("./resources/day-1.txt").readLines()
    val day = Day1(input)

    val part1 = day.solvePart1()
    println("part1: $part1") // 1031

    val part2 = day.solvePart2()
    println("part2: $part2") // 5784
}