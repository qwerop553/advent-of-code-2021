enum class RatingType{
    OXYGEN, CO2
}

fun main() {
    fun part1(input: List<String>): Int {
        // indices function gives us every valid index into the string that we're accessing
        val columns: IntRange = input[0].indices
        val gammaRate: String = buildString {
            for (column in columns) {
                val bitcount: Bitcount = input.countBitsInColumn(column)
                val (zeroes, ones) = bitcount
                val commonBit = if (zeroes > ones) "0" else "1"
                append(commonBit)
            }
        }
        val epsilonRate = gammaRate.invertBinaryString()
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun part2(input: List<String>): Int {

        fun rating(type: RatingType): String {
            val columns: IntRange = input[0].indices
            var candidates = input
            for (column in columns) {
                val (zeroes, ones) = candidates.countBitsInColumn(column)
                val mostCommon = if (zeroes > ones) '0' else '1'
                candidates = candidates.filter {
                    when (type) {
                        RatingType.OXYGEN -> it[column] == mostCommon
                        RatingType.CO2 -> it[column] != mostCommon
                    }
                }
                if (candidates.size == 1) break
            }
            return candidates.single()
        }

        return rating(RatingType.OXYGEN).toInt(2) * rating(RatingType.CO2).toInt(2)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")

    part1(input).println()
    part2(input).println()

}
private fun String.invertBinaryString() = this
    .asIterable()
    // '1' '0' produces error!
    // The character literal does not conform to the expected type CharSequence
    .joinToString(""){ if (it == '0') "1" else "0"}


private fun List<String>.countBitsInColumn(column: Int): Bitcount {
    var zeroes = 0
    var ones = 0
    for (line: String in this){
        if (line[column] == '0') zeroes++ else ones++
    }
    return Bitcount(zeroes, ones)
}
data class Bitcount(val zeroes: Int, val ones: Int)