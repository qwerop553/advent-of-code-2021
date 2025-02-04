import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

class Day07() {

    private val distanceFuel = IntArray(10000){-1}


    private fun getDistanceFuel(distance: Int): Int = if (distanceFuel[distance] >= 0) distanceFuel[distance] else distance + getDistanceFuel(distance-1)

    fun parseInput(input: List<String>): Int {
        val numbers = input.first().split(",").map{it.toInt()}
        val crabs = IntArray(numbers.max() + 1).apply{
            numbers.forEach { this[it] += 1 }
        }
        val fuels: IntArray = IntArray(numbers.max()+1)
        distanceFuel[0] = 0
        fuels.forEachIndexed { fuelIndex, _ ->
            val fuel = crabs.foldIndexed(0) { crabIndex, totalFuel: Int, crab ->
                totalFuel + getDistanceFuel(abs(crabIndex - fuelIndex)) * crab
                // totalFuel + abs(crabIndex - fuelIndex) * crab
            }
            fuels[fuelIndex] = fuel
        }
        return fuels.min()
    }
}


fun main(){
    println(Day07().parseInput(readInput("Day07")))
}