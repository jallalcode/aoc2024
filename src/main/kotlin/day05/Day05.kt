package day05

import readInput
import println

fun main() {

    fun part1(input: List<String>): Int {
        var ans = 0

        val emptyLineIndex = input.indexOf("")
        val pageOrders = input.subList(0, emptyLineIndex)
        val updates = input.subList(emptyLineIndex + 1, input.size)

        val rules = pageOrders.map { rule ->
            val (before, after) = rule.split("|").map { it.toInt() }
            before to after
        }

        for (update in updates) {
            val updatePages = update.split(",").map { it.toInt() }

            val inOrder = rules.all { (before, after) ->
                if (before in updatePages && after in updatePages) {
                    updatePages.indexOf(before) < updatePages.indexOf(after)
                } else {
                    true
                }
            }

            if (inOrder) {
                val middleIndex = updatePages.size / 2
                ans += updatePages[middleIndex]
            }
        }

        return ans
    }

    fun part2(input: List<String>): Int {
        var ans = 0

        val emptyLineIndex = input.indexOf("")
        val pageOrders = input.subList(0, emptyLineIndex)
        val updates = input.subList(emptyLineIndex + 1, input.size)

        val rules = pageOrders.map { rule ->
            val (before, after) = rule.split("|").map { it.toInt() }
            before to after
        }

        for (update in updates) {
            val updatePages = update.split(",").map { it.toInt() }.toMutableList()

            val inOrder = rules.all { (before, after) ->
                if (before in updatePages && after in updatePages) {
                    updatePages.indexOf(before) < updatePages.indexOf(after)
                } else {
                    true
                }
            }

            if (!inOrder) {
                var reordered: Boolean
                do {
                    reordered = false
                    for ((before, after) in rules) {
                        val beforeIndex = updatePages.indexOf(before)
                        val afterIndex = updatePages.indexOf(after)
                        if (beforeIndex != -1 && afterIndex != -1 && beforeIndex > afterIndex) {
                            updatePages.removeAt(afterIndex)
                            updatePages.add(beforeIndex, after)
                            reordered = true
                        }
                    }
                } while (reordered)

                val middleIndex = updatePages.size / 2
                ans += updatePages[middleIndex]
            }
        }

        return ans
    }


    // Test if implementation meets criteria from the challenge description, like:
    val testInput = readInput("day05", "day05_test")
    val testAnswer = 143
    val testResult = part1(testInput)
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day05", "day05")
    part1(input).println()
    part2(input).println()
}
