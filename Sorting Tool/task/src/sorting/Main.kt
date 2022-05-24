package sorting

import java.util.*

val scanner = Scanner(System.`in`)

/**
 * Gets input, sorts it and generates an output.
 *
 * @param -dataType: the type of data. Can be long, words, lines
 * @param -sortingType: the kind of sorting. Can be natural, byCount
 * @param -inputFile: the file that has the input. Can be any name
 * @param -outputFile: the file for the output. Can be any name
 */
fun main(args: Array<String>) {
    val acceptedCommands = Option.values().map { it.command }
    val commands = mutableMapOf<Option, OptionArgument>()
    for (arg in args) {
        if (arg.startsWith('-') && arg in acceptedCommands) {
            val option = Option.optionFromCommand[arg]!!
            if (args.indexOf(arg) == args.lastIndex || args[args.indexOf(arg) + 1].startsWith('-')) {
                println(option.missingArgumentMsg)
                continue
            }
            val argument = args[args.indexOf(arg) + 1]
            commands[option] = option.argMap(argument)
        } else if (arg.startsWith('-') && arg !in acceptedCommands) {
            println("\"$arg\" is not a valid parameter. It will be skipped.")
        } else {
            // Ignore args not starting with "-"
        }
    }
    for (option in Option.values()) {
        commands.putIfAbsent(option, option.default)
    }

    val dataType = commands[Option.DATA_TYPE] as DataType
    val sortingType = commands[Option.SORTING_TYPE] as SortingType

    val inputType = (commands[Option.INPUT_FILE]!! as InputType)
    val outputFun = (commands[Option.OUTPUT_FILE]!! as OutputType).print

    val input = parseInput(dataType, inputType)
    val sortedInput = sortInput(input, sortingType)
    generateOutput(sortedInput, dataType, sortingType, outputFun)
}

/**
 * Gets and parses the input
 *
 * @param dataType: the data type of the input
 * @param inputType: the input type
 */
fun parseInput(dataType: DataType, inputType: InputType): List<Comparable<*>> {
    return when (dataType) {
        DataType.LINE -> inputType.readLines()
        DataType.WORD -> inputType.readWords()
        DataType.LONG -> inputType.readLongs()
    }
}

/**
 * Sorts the input
 *
 * @param input: the input
 * @param sortingType: the sorting type
 */
fun sortInput(input: List<Comparable<*>>, sortingType: SortingType): List<Comparable<*>> {
    return when (sortingType) {
        SortingType.NATURAL -> input.sortedWith(compareBy { it })
        SortingType.BY_COUNT -> input.sortedWith(compareBy({ input.countElement(it) }, { it }))
    }
}

/**
 * Generated the output
 *
 * @param sortedInput: the input sorted
 * @param dataType: the data type of the input
 * @param sortingType: the sorting type
 * @param outputFun: the function used for output
 */
fun generateOutput(sortedInput: List<Comparable<*>>, dataType: DataType, sortingType: SortingType, outputFun: (String) -> Unit) {
    val total = sortedInput.size
    outputFun("Total ${dataType.type}: $total.")

    when (sortingType) {
        SortingType.NATURAL -> {
            val sep = if (dataType == DataType.LINE) "\n" else " "
            outputFun("Sorted data:$sep${sortedInput.joinToString(sep)}")
        }
        SortingType.BY_COUNT -> {
            val count = frequencyMap(sortedInput)
            val percentage = count.mapValues { (100 * it.value) / sortedInput.size }
            for (i in sortedInput.toSet()) {
                outputFun("$i: ${count[i]} times(s), ${percentage[i]}%")
            }
        }
    }
}
