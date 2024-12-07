package day06

import readInput
import println

fun main() {

    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray() }
        val rows = grid.size
        val cols = grid[0].size

        val visited = mutableSetOf<Pair<Int, Int>>()

        var startingPosition: Pair<Int, Int>? = null
        for (i in grid.indices) {
            val colIndex = grid[i].indexOf('^')
            if (colIndex != -1) {
                startingPosition = Pair(i, colIndex)
                break
            }
        }

        val directions = listOf(
            -1 to 0,
            0 to 1,
            1 to 0,
            0 to -1
        )

        var endReached = false
        var currentPos = startingPosition
        visited.add(startingPosition!!)
        var currentDirection = 0

        while (!endReached) {
            val (dy, dx) = directions[currentDirection]
            var obstacleHit = false

            while (!obstacleHit && !endReached) {
                val (y, x) = currentPos!!
                val (ny, nx) = y + dy to x + dx

                if (ny in 0 until rows && nx in 0 until cols) {
                    if (grid[ny][nx] == '#') {
                        obstacleHit = true
                    } else {
                        currentPos = ny to nx
                        visited.add(currentPos)
                    }
                } else {
                    endReached = true
                }
            }
            if (!endReached) {
                currentDirection = (currentDirection + 1) % 4
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {

        return input.size
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day06", "day06_test")
    val testAnswer = 41
    val testResult = part1(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day06", "day06")
    part1(input).println()
    part2(input).println()
}


fun isWithinBounds(y: Int, x: Int, rows: Int, cols: Int): Boolean {
    return y in 0 until rows && x in 0 until cols
}