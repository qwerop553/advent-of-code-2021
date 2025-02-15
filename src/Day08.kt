class Day08(val input: List<String>) {


    fun solvePart1() = input.map {
        it.split(" ", "|")
            .takeLast(4)
            .count{it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7}
    }
            .sum()

    fun solvePart2(): Int{

        val codes = input.map { line ->

            val _input = line
                .split("|")


            val alphabets = _input
                .first()
                .replace(" ", "")
                .groupingBy { it }
                .eachCount()

            val codes = _input
                .last()
                .split(" ")

            val AC = alphabets.filter { it.value == 8 }.keys.toList()
            val B = alphabets.filter { it.value == 6 }.keys.toList()
            val E = alphabets.filter { it.value == 4 }.keys.toList()
            val F = alphabets.filter { it.value == 9 }.keys.toList()

            var ret = ""
            codes.forEach{ code ->
                when {
                    code.length == 2 -> ret += '1'
                    code.length == 3 -> ret += '7'
                    code.length == 4 -> ret += '4'
                    code.length == 7 -> ret += '8'
                    code.length == 5 && !(code.contains(AC[0]) && code.contains(AC[1])) -> ret += '5'
                    code.length == 5 && code.contains(E[0]) -> ret += '2'
                    code.length == 5 -> ret += '3'
                    code.length == 6 && !(code.contains(AC[0]) && code.contains(AC[1])) -> ret += '6'
                    code.length == 6 && !code.contains(E[0]) -> ret += '9'
                    else -> ret += '0'
                }
            }
            ret.toInt()
        }

        return codes.sum()
    }






    }

fun main(){
    println("Part1 answer: ${Day08(readInput("Day08")).solvePart1()}")
    println("Part2 answer: ${Day08(readInput("Day08")).solvePart2()}")

}