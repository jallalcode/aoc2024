package day09

import readInput
import println

sealed class Block {
    data class File(val id: Long) : Block()
    object Space : Block()
}

data class Chunk(val content: Block, val length: Int)


fun main() {
    fun part1(input: String): Long {
        // Parse input into blocks
        val blocks = input.withIndex().flatMap { (index, char) ->
            val count = char.digitToInt()
            if (index % 2 == 0) List(count) { Block.File(index / 2.toLong()) } else List(count) { Block.Space }
        }.toMutableList()


        // Compact the disk
        var writePointer = 0
        var readPointer = blocks.size - 1
        while (writePointer < readPointer) {
            if (blocks[writePointer] is Block.Space && blocks[readPointer] is Block.File) {
                blocks[writePointer] = blocks[readPointer]
                blocks[readPointer] = Block.Space
                writePointer++
                readPointer--
            } else {
                if (blocks[writePointer] !is Block.Space) writePointer++
                if (blocks[readPointer] !is Block.File) readPointer--
            }
        }

        // Calculate checksum
        return blocks.withIndex().sumOf { (index, block) ->
            if (block is Block.File) block.id * index else 0L
        }
    }



    fun part2(input: String): Long {
        return -1L
    }


    // Test if implementation meets criteria from the challenge description
    val testInput = readInput("day09", "day09_test")
    val testAnswer = 2548L
    val testResult = part1(testInput[0])
    check(testResult == testAnswer) { "answer to test is wrong: Your answer = $testResult expected: $testAnswer" }

    val input = readInput("day09", "day09")[0]
    part1(input).println()
//    part2(listOf(input)).println()
}