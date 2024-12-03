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


        val regex = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""")
        var total = 0L
        val matches = regex.findAll(input.joinToString(""))

        /* Easier way to solve*/
        var enabled = true
        for (match in matches) {
            if (match.value == "do()") {
                enabled = true
            } else if (match.value == "don't()") {
                enabled = false
            }
            else {
                if (enabled) {
                    val (a, b) = match.destructured
                    total += a.toLong() * b.toLong()

                }
            }
        }


        /*        val split = input.joinToString("").split("don't()")
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
                }*/

        return total
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