import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

class Day07(input: List<String>) {

    private fun parseInput(input: List<String>): IntArray {
        val numbers = input.first().split(",").map{it.toInt()}
        val array =  IntArray(numbers.max() + 1).apply{
            numbers.forEach { this[it] += 1 }
        }
        return array
    }

    private val crabs = parseInput(input)

    private fun IntArray.getPos(): Int {
        val total = crabs.foldIndexed(0){ index: Int, acc: Int, i: Int ->
            acc + i * index
        }
        return floor(total.toDouble()/crabs.sum().toDouble()).toInt()
    }

    fun solvePart1(): Int{
        val center = crabs.getPos()
        val total = crabs.foldIndexed(0){
            index: Int, acc: Int, i: Int ->
            acc + i * abs(center-index)
        }
        return total
    }
}

fun main(){
    println(Day07(readInput("Day07")).solvePart1())
}