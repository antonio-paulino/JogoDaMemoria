val SYMBOLS = "#%XO&"

fun main() {
    val pairs = (SYMBOLS+SYMBOLS).toList().shuffled()
    //println(pairs)
    println("Foram gerados ${SYMBOLS.length} pares aleatórios de símbolos.")
    println("Vamos jogar!")
    var places: List<Char> = allHiddenPairs(pairs)
    var trys = 0
    do {
        println("$trys: $places")
        val first = readPosition("primeira")
        val second = readPosition("segunda")
        if ( isValidPositions(first, second, places) ) {
            places = places.play(first, second, pairs)
            trys++
        } else println("Posições inválidas")
    } while( ! isAllPairsShowed(places) )
    println("$trys: $places")
    println("Terminou o jogo usando $trys tentativas")
}

fun turnPlaces(p1: Int, p2: Int, places: List<Char>, pairs: List<Char>): List<Char> {
    var places: List<Char> = listOf()
    for (id in places.indices) {
        if (id == p1)
            places += pairs[id]
        else if (id == p2)
            places += pairs[id]
        else
            places += pairs[id]
    }
    return places
}

/*fun allHiddenPairs(pairs: List<Char>): List<Char> {
    val newList = pairs.map {
        '_'
    }
    return newList
}
*/

fun allHiddenPairs(pairs: List<Char>): List<Char> {
    var newList : List<Char> = listOf()
    for(ch in pairs) {newList += '_'}
    return newList
}

fun readPosition(question: String): Int {
    println("Indique a $question posição a virar ?")
    val position = readln().toInt()
    return position
}

fun isValidPositions(first: Int, second: Int, places: List<Char>): Boolean {
    val p1: Int = first
    val p2: Int = second
    var Bool: Boolean = false

    for (symbol in places) {
        if (symbol == '_' && p1 != p2 && p1 in places.indices && p2 in places.indices)
            Bool = true
    }
    return Bool
}

fun isAllPairsShowed(places: List<Char>): Boolean {
    var bool: Boolean = false
    for (symbol in places) {
        if (!(places.contains('_')))
            bool = true
    }
    return bool

}

fun List<Char>.play(p1: Int, p2: Int, pairs: List<Char>): List<Char> {
    val turned = turnPlaces(p1 , p2, pairs)
    if (turned[p1] != turned[p2]) {
        print(turned)
        repeat(4) { print('.'); Thread.sleep(1000) }
        print("\r \r")
        return this
    }
    return turned
}




