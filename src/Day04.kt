fun main(){

    fun part1(input: List<String>): Int {
        val draws: List<Int> = input.first().split(",").map { it.toInt() }

        val boards: List<List<String>> = input.drop(1).chunked(6).map { board ->
            board.filter { line ->
                line.isNotBlank()
            }
        }

        val boardInts: List<List<List<Int>>> = boards.map { board ->
            board.map { line ->
                line.trim()
                    .split(Regex("\\W+"))
                    .map { it.toInt() }
            }

        }

        var bingoBoards: List<BingoBoard> = boardInts.map { BingoBoard.fromCollection(it)}

        for (draw in draws){
            for (board in bingoBoards){
                board.mark(draw)
                if (board.isComplete()){
                    val sumOfUnmarkedFields = board.unmarked().sum()
                    println(sumOfUnmarkedFields * draw)
                    // 완성된 보드(board.isComplete()==True)를 제거하는 연산이구나
                    bingoBoards = bingoBoards - board
                }
            }
        }

        return 0


    }

    part1(readInput("Day04_test"))
}

data class Field(val value: Int, var marked: Boolean = false){
    fun mark(){
        marked = true
    }
}

data class BingoBoard(val fields: List<List<Field>>){

    private val widthIndices = fields[0].indices
    private val heightIndices = fields.indices

    companion object{
        fun fromCollection(coll: List<List<Int>>): BingoBoard {
            return BingoBoard(coll.map { row -> row.map { field -> Field(field) }})
        }
    }

    fun isComplete() = checkRow() || checkColumn()
    private fun checkRow() = fields.any{ row -> row.all { it.marked }}
    private fun checkColumn(): Boolean{
        for (column in widthIndices){
            var columnOk = true
            for (row in heightIndices){
                if (!fields[row][column].marked){
                    columnOk = false
                    continue
                }
            }
            if (columnOk) return true
        }
        return false
    }

    fun mark(num: Int){
        for (row in this.fields){
            row.map{
                if (it.value == num) it.mark()
            }
        }
    }

    fun unmarked() = fields.flatten().filter { !it.marked }.map{ it.value }
}



