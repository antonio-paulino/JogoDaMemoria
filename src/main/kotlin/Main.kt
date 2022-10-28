val SYMBOLS = "#%XO&" // Podem ser colocados mais ou menos símbolos.
fun main() {
    val pairs = (SYMBOLS + SYMBOLS).toList().shuffled()
    //println(pairs)
    println("Foram gerados ${SYMBOLS.length} pares aleatórios de símbolos.")
    println("Vamos jogar!")
    var places: List<Char> = allHiddenPairs(pairs)
    var trys = 0
    do {
        println("$trys: $places")
        val first = readPosition("primeira")
        val second = readPosition("segunda")
        if (isValidPositions(first, second, places)) {
            places = places.play(first, second, pairs)
            trys++
        } else println("Posições inválidas")
    } while (!isAllPairsShowed(places))
    println("$trys: $places")
    println("Terminou o jogo usando $trys tentativas")
}

fun List<Char>.play(p1: Int, p2: Int, pairs: List<Char>): List<Char> {
    val turned = this.turnPlaces(p1, p2, pairs)
    if (turned[p1] != turned[p2]) {
        print(turned)
        repeat(4) { print('.'); Thread.sleep(1000) }
        print("\r                                                       \r")
        return this
    }
    return turned
}

fun allHiddenPairs(pairs: List<Char>): List<Char> {
    return pairs.map { '_' }
}

fun readPosition(question: String): Int {
    print("Indique a $question posição a virar ? ")
    return readln().toInt()
}

fun isValidPositions(first: Int, second: Int, places: List<Char>): Boolean {
    val p1: Int = first
    val p2: Int = second
    return p1 != p2 && p1 in places.indices && p2 in places.indices && places[p1] == '_' && places[p2] == '_'
}
//fun isValidPositions(p1: Int, p2: Int, places: List<Char>) = p1 != p2 && p1 in places.indices && p2 in places.indices && places[p1] == '_' && places[p2] == '_'
fun isAllPairsShowed(places: List<Char>): Boolean {
    val listnew: List<Char> = places.filter { it == '_' }
    return !(listnew.size > 0)
}

fun List<Char>.turnPlaces(p1: Int, p2: Int, pairs: List<Char>): List<Char> {
    val places: List<Char> = this.mapIndexed { id, elem ->
        if (id == p1 || id == p2)
            pairs[id]
        else
            elem
    }
    return places
}