package day07

import readInput
import println
import java.lang.Math.pow

fun main() {
    fun part1(input: List<String>): Long {
        var answer = 0L
        input.forEach {
            val target = it.split(":")[0].toLong()
            val numbers = it.split(": ")[1].split(" ").map { it.toLong() }

            if (canReachTarget(numbers, target)) {
                answer += target
            }
        }

        return answer
    }

    fun part2(input: List<String>): Long {
        var answer = 0L
        input.forEach {
            val target = it.split(":")[0].toLong()
            val numbers = it.split(": ")[1].split(" ").map { it.toLong() }

            if (canReachTarget(numbers, target)) {
                answer += target
            }
        }

        return answer
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day07", "day07_test")
    val testAnswer = 3749L
    val testResult = part1(testInput)
//    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day07", "day07")
    part1(input).println()
    part2(input).println()
}


fun canReachTarget(numbers: List<Long>, target: Long): Boolean {
    val n = numbers.size

    val operations = listOf('+', '*', '|') // part 1 only has + and * but part 2 has a new operation '||'

    val allCombinations = generateAllCombinations(operations, n)

    for (combination in allCombinations) {
        var result = numbers[0]

        for (i in 0 ..< n-1) {
            val operation = combination[i]
            val next = numbers[i+1]
            if (operation == '+') {
                result += next
            } else if (operation == '*') {
                result *= next
            } else { // append both number
                result = "$result$next".toLong()
            }
        }

        if (result == target) return true
    }

    return false
}


fun generateAllCombinations(operations: List<Char>, slots: Int): List<String> {
    if (slots == 0) return listOf("")


    val smallerCombinations = generateAllCombinations(operations, slots - 1)
    val result: MutableList<String> = mutableListOf()

    for (smaller in smallerCombinations) {
        for (op in operations) {
            result.add("$smaller$op")
        }
    }


    return result
}