package day03

import readInput
import println

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        val regex = Regex("""mul\((\d+),(\d+)\)""")

        input.forEach { line ->
            regex.findAll(line).forEach { matchResult ->
                val (a, b) = matchResult.destructured
                total += a.toInt() * b.toInt()

            }
        }
        return total
    }

    fun part2(input: List<String>): Long {
        val regex = Regex("""mul\((\d+),(\d+)\)""")

        val split = input.joinToString("").split("don't()")
        val enabledParts = split.subList(1, split.size)

        val enabledContent = enabledParts
            .map { it.split("do()") }
            .map { it.drop(1).joinToString("") }
            .joinToString("")

        fun multi(content: String): Long {
            return regex.findAll(content).sumOf { matchResult ->
                val (a, b) = matchResult.destructured
                a.toLong() * b.toLong()
            }
        }

        return multi(split[0]) + multi(enabledContent)
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day03", "day03_test")
    val testAnswer = 48
    val testResult = part2(testInput)
    check(testResult == testAnswer.toLong()) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day03", "day03")
    part1(input).println()
    part2(input).println()
}