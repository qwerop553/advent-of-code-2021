fun main(){
    fun part1(input: List<String>): Int {
        return input
            .map{it.toInt()}
            .windowed(2)
            .count{ (a, b) -> a < b}
    }

    fun part2(input: List<String>): Int {
        return input
            .map{ it.toInt() }
            .windowed(3)
            .map{ it.sum() }
            .windowed(2)
            .count{(a, b) -> a < b }
    }

    assert(part1(readInput("Day01_test"))==7)

    assert(part2(readInput("Day01_test"))==5)

    println(part1(readInput("Day01")))

    println(part2(readInput("Day01")))
}