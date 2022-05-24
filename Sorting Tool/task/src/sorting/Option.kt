package sorting

/**
 * Represents an option that can be passed as a command line argument
 *
 * @param command: the command line argument for the option
 * @param default: the default value for the option
 * @param argMap: a mapping from strings to argument values
 * @param missingArgumentMsg: message to print when setting the option with no value
 */
enum class Option(
    val command: String,
    val default: OptionArgument,
    val argMap: (String) -> OptionArgument,
    val missingArgumentMsg: String
) {
    /**
     * The sorting type
     */
    SORTING_TYPE(
        "-sortingType", SortingType.default, SortingType.argumentFromString, "No sorting type defined!"
    ),

    /**
     * The data type
     */
    DATA_TYPE(
        "-dataType", DataType.default, DataType.argumentFromString, "No data type defined"
    ),

    /**
     * The input type
     */
    INPUT_FILE(
        "-inputFile", InputType.default, InputType.argumentFromString, "No input file defined"
    ),

    /**
     * The output type
     */
    OUTPUT_FILE(
        "-outputFile", OutputType.default, OutputType.argumentFromString, "No output file defined"
    ),
    ;

    companion object {
        /**
         * Map from strings on the command line to the corresponding option
         */
        val optionFromCommand: Map<String, Option> = buildMap {
            for (option in Option.values()) {
                this[option.command] = option
            }
        }
    }
}
