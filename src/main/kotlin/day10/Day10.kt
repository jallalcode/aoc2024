package day10

import readInput
import println

fun main() {

    val directions = listOf(
        -1 to 0,
        0 to 1,
        1 to 0,
        0 to -1
    )

    fun part1(input: List<String>): Int {

        val grid = input.map { it.toCharArray().toList() }
        var answer = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '0') {
                    val reachableNines = mutableSetOf<Pair<Int, Int>>()
                    findAllTrails(grid, i to j, reachableNines)
                    answer += reachableNines.size
                }
            }
        }

        return answer
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toCharArray().toList() }
        var totalRating = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '0') {
                    val rating = countDistinctPaths(grid, i to j)
                    totalRating += rating
                }
            }
        }

        return totalRating
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day10", "day10_test")
    val testAnswer = 81
    val testResult = part2(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day10", "day10")
    part1(input).println()
    part2(input).println()
}

fun findAllTrails(
    grid: List<List<Char>>,
    position: Pair<Int, Int>,
    reachableNines: MutableSet<Pair<Int, Int>>,
    visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
) {
    if (position in visited) return
    visited.add(position)

    val (y, x) = position
    if (grid[y][x] == '9') {
        reachableNines.add(position)
        return
    }

    val directions = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    for ((dy, dx) in directions) {
        val ny = y + dy
        val nx = x + dx
        if (ny in grid.indices && nx in grid[0].indices) {
            val currentHeight = grid[y][x].digitToInt()
            val nextHeight = grid[ny][nx].digitToInt()
            if (nextHeight == currentHeight + 1) {
                findAllTrails(grid, ny to nx, reachableNines, visited)
            }
        }
    }
}

fun countDistinctPaths(
    grid: List<List<Char>>,
    position: Pair<Int, Int>,
    visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
): Int {
    val (y, x) = position
    if (position in visited) return 0

    visited.add(position)

    if (grid[y][x] == '9') {
        visited.remove(position)
        return 1
    }

    val directions = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    var distinctPaths = 0

    for ((dy, dx) in directions) {
        val ny = y + dy
        val nx = x + dx
        if (ny in grid.indices && nx in grid[0].indices) {
            val currentHeight = grid[y][x].digitToInt()
            val nextHeight = grid[ny][nx].digitToInt()
            if (nextHeight == currentHeight + 1) {
                distinctPaths += countDistinctPaths(grid, ny to nx, visited)
            }
        }
    }

    visited.remove(position)
    return distinctPaths
}