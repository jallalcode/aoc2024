package day06

import readInput
import println

fun main() {

    fun part1(input: List<String>): Int {
        val grid = Array(input.size) { y -> Array(input[0].length) { x -> input[y][x] } }
        val visited = mutableSetOf<Pair<Int, Int>>()

        var startingPosition: Pair<Int, Int>? = null

        for (y in input.indices) {
            for (x in input[0].indices) {
                if (grid[y][x] == '^') {
                    startingPosition = y to x
                }
            }
        }

        val directions = listOf(
            -1 to 0,
            0 to 1,
            1 to 0,
            0 to -1
        )

        var endReached = false
        var currentPosition = startingPosition
        visited.add(startingPosition!!)


        while (!endReached) {

            for (direction in directions) {
                val (dy, dx) = direction
                var obstacleHit = false

                while (!obstacleHit && !endReached) {
                    val (y, x) = currentPosition!!
                    val (ny, nx) = y + dy to x + dx
                    if (ny in input.indices && nx in input[0].indices) {
                        if (grid[ny][nx] == '#') {
                            obstacleHit = true
                        }
                        else {

                            currentPosition = ny to nx
                            visited.add(currentPosition)
                        }
                    }
                    else {
                        endReached = true
                    }
                }


            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {

        return input.size
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day06", "day06_test")
    val testAnswer = 6
    val testResult = part2(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day06", "day06")
    part1(input).println()
    part2(input).println()
}


