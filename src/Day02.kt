fun main(){
    assert(part1(readInput("Day02_test"))==150)
    println(part1(readInput("Day02")))

    assert(part2(readInput("Day02_test"))==900)
    println(part2(readInput("Day02")))
}


fun part1(input: List<String>): Int {
    var hPos = 0
    var vPos = 0
    input.forEach{
        val (direction, value) = it.split(" ")
        when (direction) {
            "forward" -> hPos += value.toInt()
            "down" -> vPos += value.toInt()
            "up" -> vPos -= value.toInt()
        }
    }
    return hPos * vPos
}

fun part2(input: List<String>): Int {
    var hPos = 0
    var vPos = 0
    var aim = 0
    input.forEach{
        val (direction, value) = it.split(" ")
        when (direction) {
            "forward" -> {
                hPos += value.toInt()
                vPos += aim * value.toInt()
            }
            "down" -> {
                vPos += value.toInt()
                aim += value.toInt()
            }

            "up" -> {
                aim -= value.toInt()
                vPos -= value.toInt()
            }
        }
    }

    return hPos * vPos
}