class Day04 {
    fun part1(input: List<String>): Int {
        return input.map{ it -> it.toInt() }
            .windowed(2)
            .count { (first, second) -> first < second }
    }

    fun part2(input: List<String>): Int {
        return input.map{ it -> it.toInt()}
            .windowed(3)
            .windowed(2)
            .count{ (firstList, secondList) -> firstList.sum() < secondList.sum() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 5)

    val input = readInput("Day01")
    val input2 = readInput("Day01_2")
    part1(input).println()
    part2(input2).println()
}