import java.io.File

enum class Direction{
    N, NE, SE, S, SW, NW
}

class Hex(var x: Int, var y: Int, var z: Int) {

    fun move(dir: String) {
        when (dir) {
            "n" -> {
                y += 1
                z -= 1
            }
            "ne" -> {
                x += 1
                z -= 1
            }
            "se" -> {
                x += 1
                y -= 1
            }
            "s" -> {
                y -= 1
                z += 1
            }
            "sw" -> {
                x -= 1
                z += 1
            }
            "nw" -> {
                x -= 1
                y += 1
            }
        }
    }
}

fun main(args: Array<String>) {
    // part 1
    var hex = Hex(0, 0, 0)
    var steps = 0

    File("input/day11.txt").forEachLine { line -> run {
        val directions = line.split(",")
        for (direction in directions) {
            hex.move(direction)
        }


        while (hex.x != 0 || hex.y != 0 || hex.z != 0) {
            if (hex.x > 0) {
                hex.x--

                if (hex.y < 0) {
                    hex.y++
                } else if (hex.z < 0) {
                    hex.z++
                }
            } else if (hex.x < 0) {
                hex.x++

                if (hex.y > 0) {
                    hex.y--
                } else if (hex.z > 0) {
                    hex.z--
                }
            } else {
                if (hex.y > 0) {
                    hex.y--
                    hex.z++
                } else if (hex.y < 0) {
                    hex.y++
                    hex.z--
                }
            }

            steps++
        }
    } }

    println(steps)

    // part 2
    hex = Hex(0, 0, 0)
    val hexCopy = Hex(0, 0, 0)
    var maxSteps = 0
    steps = 0

    File("input/day11.txt").forEachLine { line -> run {
        val directions = line.split(",")
        for (direction in directions) {
            hex.move(direction)
            hexCopy.x = hex.x
            hexCopy.y = hex.y
            hexCopy.z = hex.z

            while (hexCopy.x != 0 || hexCopy.y != 0 || hexCopy.z != 0) {
                if (hexCopy.x > 0) {
                    hexCopy.x--

                    if (hexCopy.y < 0) {
                        hexCopy.y++
                    } else if (hexCopy.z < 0) {
                        hexCopy.z++
                    }
                } else if (hexCopy.x < 0) {
                    hexCopy.x++

                    if (hexCopy.y > 0) {
                        hexCopy.y--
                    } else if (hexCopy.z > 0) {
                        hexCopy.z--
                    }
                } else {
                    if (hexCopy.y > 0) {
                        hexCopy.y--
                        hexCopy.z++
                    } else if (hexCopy.y < 0) {
                        hexCopy.y++
                        hexCopy.z--
                    }
                }

                steps++
            }

            if (steps > maxSteps) maxSteps = steps
            steps = 0
        }

    } }

    println(maxSteps)
}