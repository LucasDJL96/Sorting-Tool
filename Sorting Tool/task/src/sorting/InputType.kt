package sorting

import java.io.File

/**
 * Represents the different options for the input
 *
 * @param readLines: defines how to read lines from the input
 * @param readWords: defines how to read words from the input
 * @param readLongs: defines how to read numbers from the input
 */
class InputType(
    val readLines: () -> List<String>,
    val readWords: () -> List<String>,
    val readLongs: () -> List<Long>) : OptionArgument {

    companion object {
        /**
         * The default input option: the standard input
         */
        val default = InputType(
            {
                buildList {
                    while (scanner.hasNextLine()) {
                        add(scanner.nextLine())
                    }
                }
            },
            {
                buildList {
                    while (scanner.hasNext()) {
                        add(scanner.next())
                    }
                }
            },
            {
                buildList {
                    while (scanner.hasNext()) {
                        val next = scanner.next()
                        if (!next.isNumber()) {
                            println("\"$next\" is not a long. It will be skipped.")
                            continue
                        }
                        add(next.toLong())
                    }
                }
            }
        )

        /**
         * A mapping from strings to the corresponding input option. A string corresponds
         * to a file with that name.
         */
        val argumentFromString = { name: String ->
            val blank = "\\s++".toRegex()
            InputType(
                { File(name).readLines() },
                { File(name).readText().split(blank) },
                { File(name).readText().split(blank).filter { it.isNumber() }.map { it.toLong() } }
            )
        }
    }
}
