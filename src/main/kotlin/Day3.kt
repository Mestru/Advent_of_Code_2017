import kotlin.math.abs

data class Position(val x: Int, val y: Int, val value: Int)

var valueFound = -1
var valueSearchedFor = 0

fun main(args: Array<String>) {
    // part 1
    var positions: ArrayList<Position> = arrayListOf(Position(0, 0, 1))

    var change = 1
    while (positions.get(positions.size - 1).value < args[0].toInt()) {
        for (i in 1..change) {
            positions.add(positions.size, Position(positions.get(positions.size - 1).x + 1, positions.get(positions.size - 1).y, positions.size + 1))
        }
        for (i in 1..change) {
            positions.add(positions.size, Position(positions.get(positions.size - 1).x, positions.get(positions.size - 1).y + 1, positions.size + 1))
        }
        for (i in 1..change+1) {
            positions.add(positions.size, Position(positions.get(positions.size - 1).x - 1, positions.get(positions.size - 1).y, positions.size + 1))
        }
        for (i in 1..change+1) {
            positions.add(positions.size, Position(positions.get(positions.size - 1).x, positions.get(positions.size - 1).y - 1, positions.size + 1))
        }

        change += 2
    }

    val distance = abs(0 - positions.get(args[0].toInt() - 1).x) + abs(0 - positions.get(args[0].toInt() - 1).y)
    println(distance)

    // part 2
    valueSearchedFor = args[0].toInt()
    positions = arrayListOf(Position(0, 0, 1))

    change = 1
    var x: Int
    var y: Int
    while (true) {
        for (i in 1..change) {
            x = positions.get(positions.size - 1).x + 1
            y = positions.get(positions.size - 1).y
            positions.add(positions.size, Position(x, y, getValue(positions, x, y)))
        }

        for (i in 1..change) {
            x = positions.get(positions.size - 1).x
            y = positions.get(positions.size - 1).y + 1
            positions.add(positions.size, Position(x, y, getValue(positions, x, y)))
        }

        for (i in 1..change+1) {
            x = positions.get(positions.size - 1).x - 1
            y = positions.get(positions.size - 1).y
            positions.add(positions.size, Position(x, y, getValue(positions, x, y)))
        }

        for (i in 1..change+1) {
            x = positions.get(positions.size - 1).x
            y = positions.get(positions.size - 1).y - 1
            positions.add(positions.size, Position(x, y, getValue(positions, x, y)))
        }

        if (valueFound != -1) {
            break
        }

        change += 2
    }

    println(valueFound)
}

fun getValue(positions: ArrayList<Position>, x: Int, y: Int) : Int {
    var retValue = 0
    for (element in positions) {
        if (abs(x - element.x) <= 1 && abs(y - element.y) <= 1 && (abs(x - element.x) + abs(y - element.y) != 0)) {
            retValue += element.value
        }
    }

    if (valueFound == -1 && retValue > valueSearchedFor) {
        valueFound = retValue
    }

    return retValue
}