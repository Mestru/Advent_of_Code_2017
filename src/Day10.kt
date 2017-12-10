import java.io.File

fun main(args: Array<String>) {
    // part 1
    var circularList = (0..255).toMutableList()
    var currentPosition = 0
    var skipSize = 0

    try {
        File("input/day10.txt").forEachLine { line -> run {
            val numbers = line.split(",").map { it.toInt() }

            numbers.forEach { number -> run {
                val subLength = currentPosition + number
                if (subLength > circularList.size) {
                    val reversedSubList = (circularList.subList(currentPosition, circularList.size).toList() + circularList.subList(0, subLength - circularList.size).toList()).reversed()
                    var idx = currentPosition
                    var i = 0
                    do {
                        if (idx >= circularList.size) idx -= circularList.size

                        circularList[idx] = reversedSubList[i]

                        idx++
                        i++
                    } while (idx != subLength - circularList.size)
                } else {
                    circularList.subList(currentPosition, subLength).reverse()
                }

                currentPosition += number + skipSize
                if (currentPosition >= circularList.size) {
                    currentPosition -= circularList.size
                }
                skipSize++
            } }
        } }

        println(circularList[0] * circularList[1])
    } catch (e: Exception) {
        println("First part cannot be executed when there's NaN input: " + e.message)
    }


    // part 2
    circularList = (0..255).toMutableList()
    currentPosition = 0
    skipSize = 0

    var bytes = listOf(17, 31, 73, 47, 23)
    File("input/day10.txt").forEachLine { line -> run {
        val basebytes = line.toByteArray().map { it.toInt() }
        bytes = basebytes + arrayListOf(17, 31, 73, 47, 23)
    } }

    for (iteration in 1..64)
        bytes.forEach { number -> run {
            val subLength = currentPosition + number
            if (subLength > circularList.size) {
                val reversedSubList = (circularList.subList(currentPosition, circularList.size).toList() + circularList.subList(0, subLength - circularList.size).toList()).reversed()
                var idx = currentPosition
                var i = 0
                do {
                    if (idx >= circularList.size) idx -= circularList.size

                    circularList[idx] = reversedSubList[i]

                    idx++
                    i++
                } while (idx != subLength - circularList.size)
            } else {
                circularList.subList(currentPosition, subLength).reverse()
            }

            currentPosition += number + skipSize
            while (currentPosition >= circularList.size) {
                currentPosition -= circularList.size
            }
            skipSize++
        } }

    val denseHash = ArrayList<Int>()
    for (i in 1..16) {
        denseHash.add(i-1, circularList[(i-1) * 16])
        for (j in 1+16*(i-1) until 16+16*(i-1)) {
            denseHash.set(i-1, denseHash.get(i-1).xor(circularList[j]))
        }
    }

    val sb = StringBuilder()
    denseHash.forEach { el -> run {
        sb.append(String.format("%02X", el))
    } }

    println(sb.toString().toLowerCase())

}