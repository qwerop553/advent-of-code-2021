fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        input
            .map{ it.split(" ") }
            .forEach{ when(it.first()){
                "forward" -> horizontal += it.last().toInt()
                "up" -> depth -= it.last().toInt()
                else -> depth += it.last().toInt()
            }
            }
        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0
        input
            .map{ it.split(" ") }
            .forEach{
                val command = it.first()
                val x = it.last().toInt()
                when(command){
                "forward" -> {horizontal += x; depth += aim * x}
                "up" -> aim -= x
                else -> aim += x
                }
            }
        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    // val input2 = readInput("Day01_2")
    part1(input).println()
    part2(input).println()
}
