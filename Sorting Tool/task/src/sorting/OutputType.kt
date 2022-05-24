package sorting

import java.io.File

/**
 * Represents the different options for the output
 */
class OutputType(val print: (String) -> Unit) : OptionArgument {
    companion object {
        /**
         * The default output type: the standard output
         */
        val default = OutputType(::println)

        /**
         * A mapping from strings on the command line to the corresponding output option.
         * The output corresponds to a file with said string for name
         */
        val argumentFromString = { name: String ->
            OutputType { File(name).appendText(it) }
        }
    }
}
