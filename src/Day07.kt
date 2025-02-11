import kotlin.math.abs

class Day07(input: List<String>) {

    private val crabs: Map<Int, Int> = input.first().split(",").map { it.toInt() }.groupingBy { it }.eachCount()

    private fun solve(distance: (Int) -> Int) =
        crabs.keys.asRange().map { target ->
            crabs.map { (crab, crabCount) ->
                distance(abs(crab - target)) * crabCount
            }.sum()
        }.minOf { it }



    fun solvePart1() = solve{ it }

    fun solvePart2() = solve { it * (it + 1) / 2}
}

private fun Set<Int>.asRange(): IntRange {
    return this.minOf{it}..this.maxOf{it}
}


fun main(){
    val day07: Day07 = Day07(readInput("Day07"))
    println("Answer for part 1: ${day07.solvePart1()}")
    println("Answer for part 2: ${day07.solvePart2()}")
}