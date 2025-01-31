import java.math.BigInteger
import kotlin.math.pow

fun main() {


    fun part1(input: List<String>, days: Int): BigInteger {
        var fishes = input.first().split(",").map { it.toInt() }.sorted().groupingBy { it }.eachCount().toMutableMap()
        val fihes: MutableMap<Int, BigInteger> = mutableMapOf()
        for ((k, v) in fishes) {
            fihes[k] = BigInteger(v.toString())
        }
        for (i in 1..days) {

            val newborns: BigInteger = fihes[0] ?: BigInteger("0")

            for (day in 0..7) {
                fihes[day] = fihes[day + 1] ?: BigInteger("0")
            }
            fihes[6] = fihes[6]!! + newborns
            fihes[8] = newborns
        }

        var sum = BigInteger("0")
        for (a in fihes.values) sum += a
        return sum
    }

    fun part2(input: List<String>): BigInteger? {
        val fishes = input.first().split(",").map { it.toInt() }.sorted().groupingBy { it }.eachCount()
        var sum = BigInteger.ZERO
        for (key in 0..8)
            sum += (fishes.getOrDefault(0, 0) * (2.0.pow(((80 - key) / 7)))).toInt().toBigInteger()

        return sum
    }




    check(part2(readInput("Day06_test"))==BigInteger("5934"))
    //println(part1(readInput("Day06"), 256))


}