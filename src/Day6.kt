import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println(getNumberOfChanges(args))
    println(getNumberOfCycles(args))
}
// part 1
fun getNumberOfChanges(args: Array<String>): Int {
    var numberOfChanges = 0
    val listOfNumbers = LinkedList<Int>()
    val snapshot = ArrayList<String>()
    var maxIndex: Int
    var amountToDistribute: Int
    var newSnapshot: String

    for (arg in args) {
        listOfNumbers.add(arg.toInt())
    }

    while (true) {
        maxIndex = listOfNumbers.indexOf(listOfNumbers.max())
        amountToDistribute = listOfNumbers.get(maxIndex)
        newSnapshot = listOfNumbers.toArray().contentToString()
        listOfNumbers.set(maxIndex, 0)
        for (s in snapshot) {
            if (newSnapshot.equals(s)) return numberOfChanges
        }
        snapshot.add(newSnapshot)


        while (amountToDistribute > 0) {
            maxIndex++
            if (maxIndex >= listOfNumbers.size) maxIndex = 0
            listOfNumbers.set(maxIndex, listOfNumbers.get(maxIndex) + 1)

            amountToDistribute--
        }

        numberOfChanges++

    }
}

// part 2
fun getNumberOfCycles(args: Array<String>): Int {
    val listOfNumbers = LinkedList<Int>()
    val snapshot = LinkedList<String>()
    var maxIndex: Int
    var amountToDistribute: Int
    var newSnapshot: String

    for (arg in args) {
        listOfNumbers.add(arg.toInt())
    }

    while (true) {
        maxIndex = listOfNumbers.indexOf(listOfNumbers.max())
        amountToDistribute = listOfNumbers.get(maxIndex)
        newSnapshot = listOfNumbers.toArray().contentToString()
        listOfNumbers.set(maxIndex, 0)
        for (s in snapshot) {
            if (newSnapshot.equals(s)) {
                return snapshot.size - snapshot.indexOf(s)
            }
        }
        snapshot.add(newSnapshot)


        while (amountToDistribute > 0) {
            maxIndex++
            if (maxIndex >= listOfNumbers.size) maxIndex = 0
            listOfNumbers.set(maxIndex, listOfNumbers.get(maxIndex) + 1)

            amountToDistribute--
        }
    }
}