package sorting

/**
 * Represents the different options for sorting the input
 *
 * @param command: the string to be passed as an argument on the command line
 */
enum class SortingType(val command: String) : OptionArgument {
    /**
     * The natural ordering
     */
    NATURAL("natural"),

    /**
     * Ordering by count
     */
    BY_COUNT("byCount"),
    ;

    companion object {
        /**
         * The default value if the option is not specified
         */
        val default = NATURAL

        /**
         * A mapping from arguments as strings to the corresponding sorting type
         */
        val argumentFromString = { name: String ->
            var result = default
            for (type in SortingType.values()) {
                if (name == type.command) result = type
            }
            result
        }
    }
}
