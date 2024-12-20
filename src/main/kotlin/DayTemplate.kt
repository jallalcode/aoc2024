package dayX

import readInput
import println

fun main() {
    fun part1(input: List<String>): Int {
        // Solution for part 1
        return input.size
    }

    fun part2(input: List<String>): Int {
        // Solution for part 2
        return input.size
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("dayX", "DayX_test")
    val testAnswer = 0
    val testResult = part1(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("dayX", "DayX")
    part1(input).println()
    part2(input).println()
}
