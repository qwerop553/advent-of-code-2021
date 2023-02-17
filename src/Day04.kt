fun main(){
    val input = readInput("Day04_test")

    val draws = input.first().split(",").map { it.toInt() }

    val boards: List<List<String>> = input.drop(1).chunked(6).map{ board ->
        board.filter { line ->
            line.isNotBlank()
        }
    }

    boards.map{ board ->
        board.map { line ->
            
        }
    }
}