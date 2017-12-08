import java.io.File
import kotlin.math.abs

data class Program(var weight: Int, var parent: String?, var children: List<String>?)
val programs = HashMap<String, Program>()


fun main(args: Array<String>) {
    // part 1
    val lineRegex = "(\\w+) \\((\\d+)\\)( -> )?(.+)?".toRegex()

    var name: String
    var weight: Int
    var children: List<String> = ArrayList()

    File("input/day7.txt").forEachLine { line -> run {
        name = lineRegex.matchEntire(line)?.groups?.get(1)!!.value
        weight = lineRegex.matchEntire(line)?.groups?.get(2)!!.value.toInt()
        if (lineRegex.matchEntire(line)?.groups?.get(3)?.value != null) {
            children = lineRegex.matchEntire(line)?.groups?.get(4)?.value!!.split(", ")
        }

        children.forEach { child -> run {
            programs.getOrPut(child, { Program(-1, name, null) }).parent = name
        }}

        programs.getOrPut(name, { Program(weight, null, children) }).weight = weight
        programs.get(name)!!.children = children

        children = ArrayList()
    } }

    var parent = Program(0, null, null)
    programs.forEach { program -> run {
        if (program.value.parent == null) {
            println(program.key)
            parent = program.value
        }
    } }

    // part 2
    balanceWeight(parent)
}

fun balanceWeight(program: Program): Int {
    var weight = program.weight
    var childWeight: Int
    val childrenWeight = ArrayList<Int>()
    if (program.children != null) {
        for (child in program.children!!) {
            childWeight = balanceWeight(programs.getOrDefault(child, Program(0, null, null)))
            childrenWeight.add(childWeight)
            weight += childWeight
        }
    }

    if (childrenWeight.size > 0) {
        val testedWeight = childrenWeight.get(0)
        var badWeight = -1
        childrenWeight.forEach { childsWeight -> run {
            if (childsWeight != testedWeight) {
                badWeight = childsWeight
            }
        } }

        if (badWeight != -1 ) {
            println(abs(testedWeight - badWeight))
        }
    }

    return weight
}