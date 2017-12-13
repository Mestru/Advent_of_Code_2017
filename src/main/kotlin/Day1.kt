fun main(args: Array<String>) {
    // part 1
    val input = args[0]
    var sum = 0
    var idx = 0
    for (i in input.indices) {
        idx = if (i - 1 < 0) input.length else i
        if (input[idx-1] == input[i]) {
            sum += input[idx-1].toString().toInt()
        }
    }

    println(sum)

    // part 2
    sum = 0
    idx = 0
    for (i in input.indices) {
        idx = if (input.length / 2 + i < input.length) input.length / 2 + i else (input.length / 2 + i) - input.length
        if (input[i] == input[idx]) {
            sum += input[i].toString().toInt()
        }
    }

    println(sum)
}