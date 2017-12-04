import java.io.File

val letters = "abcdefghijklmnopqrstuvwxyz".toCharArray()

fun main(args: Array<String>) {
    // part 1
    var numberOfCorrectPassphrases = 0
    File("input/day4.txt").forEachLine { line -> run {
        if (passphraseIsValid(line)) numberOfCorrectPassphrases++
    }
    }

    println(numberOfCorrectPassphrases)

    // part 2
    numberOfCorrectPassphrases = 0
    File("input/day4.txt").forEachLine { line -> run {
        val words = line.split(Regex("[ \t]"))
        val o1 = HashMap<Char, Int>()
        val o2 = HashMap<Char, Int>()
        for (letter in letters) {
            o1.put(letter, 0)
            o2.put(letter, 0)
        }

        var isPassphraseCorrect = true
        for (i in 0 until words.size) {
            for (letter in letters) {
                o1.put(letter, 0)
            }
            words[i].toCharArray().forEach { c -> o1.put(c, o1.getValue(c) + 1) }
            for (j in 0 until words.size) {
                for (letter in letters) {
                    o2.put(letter, 0)
                }
                words[j].toCharArray().forEach { c -> o2.put(c, o2.getValue(c) + 1) }
                if (i != j && mapsEqual(o1, o2)) isPassphraseCorrect = false
            }
        }

        if (isPassphraseCorrect) numberOfCorrectPassphrases++
    }
    }

    println(numberOfCorrectPassphrases)

}

fun passphraseIsValid(line : String) : Boolean {
    val words = line.split(Regex("[ \t]"))
    for (i in 0 until words.size) {
        for (j in 0 until words.size) {
            if (i != j && words[i].equals(words[j])) return false
        }
    }

    return true
}

fun mapsEqual(o1: HashMap<Char, Int>, o2: HashMap<Char, Int>): Boolean {
    for (letter in letters) {
        if (o1.get(letter) != o2.get(letter)) return false
    }

    return true
}