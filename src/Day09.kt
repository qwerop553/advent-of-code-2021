import kotlin.math.abs

class Day09(input: List<String>) {

    private val rows = input.indices
    private val cols = input[0].indices

    private val input = input.map {
        it.map{
            it.toString().toInt()
        }.toMutableList()
    }.toMutableList()

    fun solvePart1(): Int {
        var ret = 0
        for (row in rows){
            for (col in cols){
                ret += input.checkLowest(row, col)
            }
        }
        return ret
    }

    fun solvePart2(): Int {
        val testList = mutableListOf<Int>()
        for (row in rows) {
            for (col in cols) {
                if (input.checkLowest(row, col) != 0) testList.add(findSizeOfBasin(row, col))
            }
        }
        // sort 와 sorted 의 차이는 이런 것이군요
        return testList.sorted().takeLast(3).reduce { acc: Int, i: Int ->
            acc * i
        }
    }

    fun findSizeOfBasin(row: Int, col: Int): Int{
        if (input[row][col]==9) return 0
        var temp = 1
        val testCase = listOf(Pair(row, col-1), Pair(row, col+1), Pair(row-1, col), Pair(row+1, col))
            .filter{(r, c) -> 0 <= r && r <= input.lastIndex && 0 <= c && c <= input[0].lastIndex}
            .filter {(r, c) -> input[row][col] < input[r][c] && input[r][c] < 9 }
        input[row][col] = 9
        testCase
            .forEach{(r, c) -> temp += findSizeOfBasin(r, c)}

        return temp
        }


    }




private fun List<List<Int>>.checkLowest(row: Int, col: Int): Int {
    val a  = row-1..row+1
    val b = col-1..col+1
    val pairs = a.flatMap{sit -> b.map{ Pair(sit, it)}}.filter{(first, second) ->
        0 <= first && first <= this.indices.max() && 0 <= second && second <= this[0].indices.max() &&
                abs(first - row) + abs(second - col) == 1}
    for ((r, c) in pairs) if (this[row][col] >= this[r][c]) return 0
    return this[row][col] + 1
    }





fun main() {

    println("Part 1: ${Day09(readInput("Day09")).solvePart1()}")
    println("Part 2: ${Day09(readInput("Day09")).solvePart2()}")
}
