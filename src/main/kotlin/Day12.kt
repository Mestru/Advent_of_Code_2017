import java.io.File

data class P(val id: Int, val pipesTo: ArrayList<Int>)
fun checkPipes(programs: ArrayList<P>, callers: ArrayList<Int>, pipes: ArrayList<Int>, group: Int): Boolean {
    pipes.forEach { pipe -> run {
        if (pipe == group) return true
        if (!callers.contains(pipe)) {
            callers.add(pipe)
            if (checkPipes(programs, callers, programs[pipe].pipesTo, group)) return true
        }
    } }
    return false
}

fun main(args: Array<String>) {
    // part 1
    val programs = ArrayList<P>()
    var programsIn0Group = 0

    File("input/day12.txt").forEachLine { line -> run {
        val elements = line.split(" <-> ")
        val pipes = elements[1].split(", ").map { it -> it.toInt() }

        programs.add(P(elements[0].toInt(), pipes as ArrayList<Int>))
    } }

    programs.forEach { program -> run {
        val callers = ArrayList<Int>()
        callers.add(program.id)
        if (checkPipes(programs, callers, program.pipesTo, 0))
            programsIn0Group++
    } }

    println(programsIn0Group)

    // part 2
    var numberOfGroups = 0
    val usedPrograms = ArrayList<Int>()

    programs.forEach { groupProgram -> run {
        if (!usedPrograms.contains(groupProgram.id)) {
            programs.forEach { program -> run {
                val callers = ArrayList<Int>()
                callers.add(program.id)
                if (checkPipes(programs, callers, program.pipesTo, groupProgram.id)) {
                    usedPrograms.add(program.id)
                }
            } }
            numberOfGroups++
        }
    } }

    println(numberOfGroups)
}
