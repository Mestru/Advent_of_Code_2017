import java.io.File
import java.util.*

fun main(args: Array<String>) {
    // part 1
    var instructions = LinkedList<Int>()

    File("input/day5.txt").forEachLine { line -> run {
        instructions.add(line.toInt())
    } }

    var i = 0
    var numberOfSteps = 0
    while (i < instructions.size && i >= 0) {
        i += instructions.set(i, instructions.get(i) + 1)
        numberOfSteps++
    }

    println(numberOfSteps)

    // part 2
    instructions = LinkedList()

    File("input/day5.txt").forEachLine { line -> run {
        instructions.add(line.toInt())
    } }

    i = 0
    numberOfSteps = 0
    var increaseAmount: Int
    while (i < instructions.size && i >= 0) {
        increaseAmount = if (instructions.get(i) >= 3) -1 else 1
        i += instructions.set(i, instructions.get(i) + increaseAmount)
        numberOfSteps++
    }

    println(numberOfSteps)

}