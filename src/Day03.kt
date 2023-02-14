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
        val columns: IntRange = input[0].indices

        var tempInput_oxygen = input
        columns.forEach { columnNo ->
            val (zeroes, ones) = tempInput_oxygen.countBitsInColumn(columnNo)
            if (tempInput_oxygen.size == 1){}
            else {
                tempInput_oxygen = if (zeroes > ones) tempInput_oxygen
                    .filter { line -> line[columnNo] == '0' } else tempInput_oxygen.filter { line -> line[columnNo] == '1' }
            }
        }

        var tempInput_scrubber = input
        columns.forEach{columnNo ->
            val (zeroes, ones) = tempInput_scrubber.countBitsInColumn(columnNo)
            if (tempInput_scrubber.size == 1){}
            else {
                tempInput_scrubber = if (zeroes > ones) tempInput_scrubber
                    .filter { line -> line[columnNo] == '1' } else tempInput_scrubber.filter { line -> line[columnNo] == '0' }
            }
        }
        val oxygenRate = tempInput_oxygen[0].toInt(2)
        val scrubberRate = tempInput_scrubber[0].toInt(2)
        return oxygenRate * scrubberRate
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