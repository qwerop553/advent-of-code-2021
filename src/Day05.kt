import kotlin.math.abs

fun main(){

    fun part1(input: List<String>): Any{

        val lines = inputToListOfLines(input)

        return lines.filter{ it.isHorizontalOrVertical() }.countOverlappedPoints()
    }

    fun part2(input: List<String>): Any{

        val lines = inputToListOfLines(input)

        return lines.countOverlappedPoints()
    }
    assert(part1(readInput("Day05_test"))==5)
    println("Result part1: ${part1(readInput("Day05"))}")

    assert(part2(readInput("Day05_test"))==12)
    println("Result part1: ${part2(readInput("Day05"))}")
}

fun List<Line>.countOverlappedPoints(): Int{
    val allPoints =  this.map{ line ->
        line.getPoints()
    }.flatten()
    return allPoints.groupBy{point -> point}.filter { it.value.size > 1 }.count()
}


fun inputToListOfLines(input: List<String>): List<Line> {
    val segments: List<List<List<Int>>> = input.map { line ->
        line.split(" -> ").map { point ->
            point.split(",").map { num ->
                num.toInt()
            }
        }
    }

    val lines = segments.map { line ->
        Line.fromCollection(line)
    }
    return lines
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {

    companion object {
        fun fromCollection(line: List<List<Int>>): Line {
            return Line(line[0][0], line[0][1], line[1][0], line[1][1])
        }

    }

    fun isHorizontalOrVertical(): Boolean = isVertical() || isHorizontal()
    private fun isHorizontal(): Boolean = y1 == y2
    private fun isVertical(): Boolean = x1 == x2

    fun getPoints(): List<Pair<Int, Int>> {
        return pointsInLines()
    }

    private fun pointsInLines(points: List<Pair<Int, Int>> = mutableListOf()): List<Pair<Int, Int>> {
        val ret = points.toMutableList()
        when (isHorizontalOrVertical()) {
            true -> when (isHorizontal()) {
                true -> {
                    val min = if (x1 < x2) x1 else x2
                    val max = if (x1 < x2) x2 else x1
                    for (i in min..max) {
                        ret += Pair(i, y1)
                    }
                }

                false -> {
                    val min = if (y1 < y2) y1 else y2
                    val max = if (y1 < y2) y2 else y1
                    for (i in min..max) {
                        ret += Pair(x1, i)
                    }
                }
            }

            false -> { // Diagonal
                for (i in 0..abs(x2 - x1)) {
                    ret += if (x1 < x2) {
                        if (y1 < y2) Pair(x1 + i, y1 + i)
                        else Pair(x1 + i, y1 - i)
                    } else {
                        if (y1 < y2) Pair(x1 - i, y1 + i)
                        else Pair(x1 - i, y1 - i)
                    }

                }
            }
        }
        return ret
    }


}

