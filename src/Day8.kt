import java.io.File

fun main(args: Array<String>) {
    val inputRegex = "(\\w+) (\\w+) (-?\\d+) if (\\w+) (.+) (-?\\d+)".toRegex()

    val registries = HashMap<String, Int>()

    var registry: String
    var increase: Boolean
    var amount: Int
    var conditionedRegistry: String
    var conditionedAmount: Int

    var absoluteMax = -999999

    File("input/day8.txt").forEachLine { line -> run {
        registry = inputRegex.matchEntire(line)?.groups?.get(1)?.value!!
        increase = inputRegex.matchEntire(line)?.groups?.get(2)?.value?.equals("inc")!!
        amount = inputRegex.matchEntire(line)?.groups?.get(3)?.value?.toInt()!!
        conditionedRegistry = inputRegex.matchEntire(line)?.groups?.get(4)?.value!!
        conditionedAmount = inputRegex.matchEntire(line)?.groups?.get(6)?.value?.toInt()!!
        when(inputRegex.matchEntire(line)?.groups?.get(5)?.value!!) {
            in ">" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) > conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
            in "<" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) < conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
            in "==" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) == conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
            in "!=" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) != conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
            in ">=" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) >= conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
            in "<=" -> {
                if (registries.getOrPut(conditionedRegistry, { 0 }) <= conditionedAmount) {
                    registries.set(registry, registries.getOrPut(registry, {0}) + if (increase) amount else -amount)
                }
            }
        }

        registries.forEach { entry -> run {
            if (entry.value > absoluteMax) absoluteMax = entry.value
        } }
    } }

    var max = -999999
    registries.forEach { entry -> run {
        if (entry.value > max) max = entry.value
    } }

    registries.forEach { entry -> run {
        if (entry.value > absoluteMax) absoluteMax = entry.value
    } }

    println(max)
    println(absoluteMax)
}