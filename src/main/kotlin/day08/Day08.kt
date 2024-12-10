package day08

import readInput
import println

fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray() }



        return input.size
    }

    fun part2(input: List<String>): Int {
        // Solution for part 2
        return input.size
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day08", "day08_test")
    val testAnswer = 14
    val testResult = part1(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day08", "day08")
    part1(input).println()
    part2(input).println()
}
