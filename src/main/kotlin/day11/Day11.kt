package day11

import readInput
import println

fun main() {
    fun part1(input: List<String>): Long {

        val stones: MutableList<Long> = input[0].split(" ").map { it.toLong() }.toMutableList()

        for (i in 0..24) {
            println("iteration $i")
            val iterator = stones.listIterator()
            while (iterator.hasNext()) {
                val stone = iterator.next()
                if (stone == 0L) {
                    iterator.set(1L)
                } else if (stone.toString().length % 2 == 0) {
                    val tempStone1 = stone.toString().substring(0, stone.toString().length / 2).toLong()
                    val tempStone2 = stone.toString().substring(stone.toString().length / 2).toLong()
                    iterator.set(tempStone1)
                    iterator.add(tempStone2)
                } else {
                    val tempStone = stone * 2024
                    iterator.set(tempStone)
                }
            }
        }


        return stones.size.toLong()
    }

    fun part2(input: List<String>): Long {
        var stoneCounts = mutableMapOf<Long, Long>()
        input[0].split(" ").map { it.toLong() }.forEach {
            stoneCounts[it] = stoneCounts.getOrDefault(it, 0) + 1
        }

        repeat(75) { iteration ->
            println("iteration $iteration")
            val newStoneCounts = mutableMapOf<Long, Long>()

            for ((stone, count) in stoneCounts) {
                if (stone == 0L) {
                    newStoneCounts[1L] = newStoneCounts.getOrDefault(1L, 0) + count
                } else {
                    val length = stone.toString().length
                    if (length % 2 == 0) {
                        val mid = length / 2
                        val stoneStr = stone.toString()
                        val tempStone1 = stoneStr.substring(0, mid).toLong()
                        val tempStone2 = stoneStr.substring(mid).toLong()
                        newStoneCounts[tempStone1] = newStoneCounts.getOrDefault(tempStone1, 0) + count
                        newStoneCounts[tempStone2] = newStoneCounts.getOrDefault(tempStone2, 0) + count
                    } else {
                        val tempStone = stone * 2024
                        newStoneCounts[tempStone] = newStoneCounts.getOrDefault(tempStone, 0) + count
                    }
                }
            }

            stoneCounts = newStoneCounts
        }

        return stoneCounts.values.sum()
    }

    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day11", "day11_test")
    val testAnswer = 55312L
    val testResult = part1(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day11", "day11")
    part1(input).println()
    part2(input).println()
}
