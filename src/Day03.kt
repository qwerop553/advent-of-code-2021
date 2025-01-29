fun main() {

    fun part1(input: List<String>): Int {
        val gamma: String = buildString {
            for (column in input[0].indices) {
                val (zeroes, ones) = input.countBitsInColumn(column)
                val commonBit = if (zeroes > ones) "0" else "1"
                append(commonBit)
            }
        }
        val epsilon = buildString {
            gamma
                .asIterable()
                .forEach{ if (it == '0') append('1') else append('0')}
        }
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int{
        fun rating(type: RatingType): String {
            val columns = input[0].indices
            var candidate = input
            for (column in columns){
                val (zeroes, ones) = candidate.countBitsInColumn(column)
                val mostCommon = if (zeroes > ones) '0' else '1'
                when(type){
                    RatingType.OXYGEN ->  candidate = candidate.filter { it[column] == mostCommon }
                    RatingType.CO2 -> candidate = candidate.filter { it[column] != mostCommon }
                }

                if (candidate.size == 1) break
            }
            return candidate.single()
        }

        return rating(RatingType.OXYGEN).toInt(2) * rating(RatingType.CO2).toInt(2)
    }

    assert(part1(readInput("Day03_test"))==198)
    println(part1(readInput("Day03")))

    println(part2(readInput("Day03")))
}

private fun List<String>.countBitsInColumn(column: Int): BitCount{
        var zeroes = 0
        var ones = 0
        for (line in this) {
            if (line[column] == '0') zeroes++ else ones++
        }
        return BitCount(zeroes, ones)
}

data class BitCount(val zeroes: Int, val ones: Int)

private enum class RatingType{
    OXYGEN,
    CO2
}