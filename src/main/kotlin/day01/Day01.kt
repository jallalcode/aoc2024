package day01

import readInput
import println
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var totalDistance = 0
        val leftList: MutableList<Int> = mutableListOf()
        val rightList: MutableList<Int> = mutableListOf()

        for (line in input) {
            val values = line.split("   ")
            val left = values[0].toInt()
            val right = values[1].toInt()
            leftList.add(left)
            rightList.add(right)
        }

        leftList.sort()
        leftList.reverse()
        rightList.sort()
        rightList.reverse()

        for (i in 0..<leftList.size) {
            totalDistance += max(leftList[i], rightList[i]) - min(leftList[i], rightList[i])
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val leftList: MutableList<Int> = mutableListOf()
        val rightList: MutableList<Int> = mutableListOf()
        val similairityScores: MutableList<Int> = mutableListOf()

        for (line in input) {
            val values = line.split("   ")
            val left = values[0].toInt()
            val right = values[1].toInt()
            leftList.add(left)
            rightList.add(right)
        }



        for (i in 0..<leftList.size) {

            val similairityScore = rightList.count {
                it == leftList[i]
            }

            similairityScores.add(leftList[i] * similairityScore)
        }

        return similairityScores.sum()
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day01", "day01_test")
    val testAnswer = 31
    check(part2(testInput) == testAnswer) { "answer to test is wrong" }

    val input = readInput("day01", "day01")
    part1(input).println()
    part2(input).println()
}
