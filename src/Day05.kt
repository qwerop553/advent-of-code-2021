fun main(){

    fun part1(input: List<String>): List<Line>{

        val input = input.map{ line ->
            val (point1, point2) = line.split(" -> ")
            val (x1, y1) = point1.split(",")
            val (x2, y2) = point2.split(",")
            Line(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
        }

        return input
    }

    println("Result part1: ${part1(readInput("Day05_test"))}")
    // assert(part1(readInput("Day05_test"))==5)
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int){
    fun isHorizontal(): Boolean = y1 == y2
    fun isVertical(): Boolean = x1 == x2
    //


    fun isHorizontalOrVertical(): Boolean = isVertical() || isHorizontal()

}