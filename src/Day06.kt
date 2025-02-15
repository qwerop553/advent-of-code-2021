class Day06(input: List<String>) {

    private val fishesPerDay: LongArray = parseInput(input)

    private fun parseInput(input: List<String>): LongArray =
        LongArray(9).apply { // apply는 정말 receiver로 사용하기 위해서 호출한 것 뿐이구나.
                // 리뷰 전 코드: input.map{ it.split(",").map { it.toInt() }.forEach { this[it] += 1L }
                input.first().split(",").map { it.toInt() }.forEach { this[it] += 1L }
        }

    fun solvePart1(): Long =
        simulateDays(80)

    fun solvePart2(): Long =
        simulateDays(256)

    private fun simulateDays(days: Int): Long {
        repeat(days) {
            fishesPerDay.rotateLeftInPlace()
            fishesPerDay[6] += fishesPerDay[8]
        }
        return fishesPerDay.sum()
    }

    private fun LongArray.rotateLeftInPlace() {
        val leftMost = first()
        this.copyInto(this, startIndex = 1)
        this[this.lastIndex] = leftMost
    }
}

/*
fun main(){
    val input = readInput("Day06")
    println("Day 6: ${Day06(input).solvePart1()}")
    println("Day 6: ${Day06(input).solvePart2()}")
}
 */