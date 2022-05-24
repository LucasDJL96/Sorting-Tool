package sorting

/**
 * Represents the different data types to work with
 *
 * @param type: a string description of the type of data
 * @param command: the corresponding command line string
 */
enum class DataType(val type: String, val command: String) : OptionArgument {
    /**
     * Numbers
     */
    LONG("numbers", "long"),

    /**
     * Lines
     */
    LINE("lines", "line"),

    /**
     * Words
     */
    WORD("words", "word"),
    ;

    companion object {
        /**
         * The default data type
         */
        val default = WORD

        /**
         * A mapping from strings in the command line to the corresponding data types
         */
        val argumentFromString = { name: String ->
            var result = default
            for (type in DataType.values()) {
                if (name == type.command) result = type
            }
            result
        }
    }
}
