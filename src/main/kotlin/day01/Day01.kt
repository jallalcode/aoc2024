package day01

import readInput
import println
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        val (leftList, rightList) = input
            .map { it.split("   ").map(String::toInt) }
            .map { it[0] to it[1] }
            .unzip()

        return leftList.sorted().zip(rightList.sorted())
            .sumOf { (left, right) -> abs(left - right) }
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = input
            .map { it.split("   ").map(String::toInt) }
            .map { it[0] to it[1] }
            .unzip()

        return leftList.sumOf { left -> left * rightList.count { it == left } }
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day01", "day01_test")
    val testAnswer = 31
    check(part2(testInput) == testAnswer) { "answer to test is wrong" }

    val input = readInput("day01", "day01")
    part1(input).println()
    part2(input).println()
}
