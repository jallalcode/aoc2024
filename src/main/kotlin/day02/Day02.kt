package day02

import readInput
import println
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        // Solution for part 1
        return input.count {
            val levels = it.split(" ").map { it.toInt() }
            isSafe(levels)
        }
    }

    fun part2(input: List<String>): Int {
        var total = 0

        input.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }

            if (isSafe(levels)) {
                total++
                return@forEach
            }

            for (i in levels.indices) {
                val modifiedLevels = levels.toMutableList()
                modifiedLevels.removeAt(i)
                if (isSafe(modifiedLevels)) {
                    total++
                    break
                }
            }
        }

        return total
    }



    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day02", "day02_test")
    val testAnswer = 4
    val testResult = part2(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day02", "day02")
    part1(input).println()
    part2(input).println()
}

private fun isSafe(levels: List<Int>): Boolean {
    val pairs = levels.zipWithNext()
    val isIncreasing = pairs.all { it.first < it.second }
    val isDecreasing = pairs.all { it.first > it.second }
    val allDifferencesValid = pairs.all { abs(it.first - it.second) in 1..3 }

    return allDifferencesValid && (isIncreasing || isDecreasing)
}
