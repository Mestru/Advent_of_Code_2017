import java.io.File

fun main(args: Array<String>) {
    // part 1
    var sum = 0
    var smallest = 10000000
    var biggest = 0
    File("input/day2.txt").forEachLine { line ->
        run {
            for (element in line.split(Regex("[ \t]"))) {
                if (element.toInt() < smallest) smallest = element.toInt()
                if (element.toInt() > biggest) biggest = element.toInt()
            }
        }
        sum += biggest - smallest
        smallest = 10000000
        biggest = 0
    }

    println(sum)

    // part 2
    sum = 0

    File("input/day2.txt").forEachLine { line ->
        run {
            for (element1 in line.split(Regex("[ \t]"))) {
                for (element2 in line.split(Regex("[ \t]"))) {
                    if (!element1.equals(element2) && element1.toInt() % element2.toInt() == 0) sum += element1.toInt() / element2.toInt()
                }
            }
        }
    }

    println(sum)
}