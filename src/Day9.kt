import java.io.File

fun main(args: Array<String>) {
    var openedGroups = 0
    var isGarbage = false
    var isIgnoring = false
    var score = 0
    var numberOfGarbageCharacters = 0

    // part 1
    File("input/day9.txt").forEachLine { line -> run {
        val characters = line.toCharArray()
        characters.forEach { c -> run {

            if (!isGarbage && !isIgnoring) {
                if (c == '{') {
                    openedGroups++
                }
                else if (c == '}' && openedGroups > 0) {
                    score += openedGroups
                    openedGroups--
                }
                else if (c == '<') {
                    isGarbage = true
                }
                else if (c == '!') {
                    isIgnoring = true
                }
                else {
                    // ignore rest
                }

            } else if (isGarbage && !isIgnoring) {
                if (c == '>') {
                    isGarbage = false
                } else if (c == '!') {
                    isIgnoring = true
                } else {
                    // part 2 in this branch
                    numberOfGarbageCharacters++
                }

            } else {
                isIgnoring = false
            }
        } }
    } }

    println(score)
    println(numberOfGarbageCharacters)
}