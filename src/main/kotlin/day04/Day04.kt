package day04

import readInput
import println

fun main() {
    val directions = listOf(
        -1 to 0,
        1 to 0,
        0 to -1,
        0 to 1,
        -1 to -1,
        1 to 1,
        -1 to 1,
        1 to -1
    )

    fun part1(input: List<String>): Int {
        // Solution for part 1
        val grid = Array(input.size) { y -> Array(input[0].length) { x -> input[y][x] } }
        var totalXmases = 0

        for (y in input.indices) {
            for (x in input[y].indices) {

                if (grid[y][x] == 'X') {
                    for (direction in directions) {
                        val (dx, dy) = direction
                        val (nx, ny) = x + dx to y + dy

                        if (nx in grid[y].indices && ny in grid.indices && grid[ny][nx] == 'M') {
                            if (nx + dx in grid[y].indices && ny + dy in grid.indices && grid[ny + dy][nx + dx] == 'A') {
                                if (nx + 2 * dx in grid[y].indices && ny + 2 * dy in grid.indices && grid[ny + 2 * dy][nx + 2 * dx] == 'S') {
                                    totalXmases++
                                }
                            }
                        }
                    }
                }

            }
        }

        return totalXmases
    }

    fun part2(input: List<String>): Int {
        val grid = Array(input.size) { y -> Array(input[0].length) { x -> input[y][x] } }
        var totalMases = 0

        for (y in input.indices) {
            for (x in input[y].indices) {
                if (grid[y][x] == 'A') {
                    if (
                        y - 1 in grid.indices && y + 1 in grid.indices &&
                        x - 1 in grid[y].indices && x + 1 in grid[y].indices
                    ) {
                        val botLeft = grid[y - 1][x - 1]
                        val botRight = grid[y - 1][x + 1]
                        val topLeft = grid[y + 1][x - 1]
                        val topRight = grid[y + 1][x + 1]

                        val a = listOf(botLeft, 'A', topRight).joinToString("").trim()
                        val b = listOf(botRight, 'A', topLeft).joinToString("").trim()



                        if ((a == "MAS" || a.reversed() == "MAS") && (b == "MAS" || b.reversed() == "MAS")) {
                            totalMases++
                        }
                    }
                }
            }
        }

        return totalMases
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day04", "day04_test")
    val testAnswer = 9
    val testResult = part2(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day04", "day04")
    part1(input).println()
    part2(input).println()
}


