package sorting

/**
 * Takes an iterable to a map where the keys are the elements of the iterable and
 * the values are the times they are repeated in the iterable
 *
 * @param iterable: the iterable
 */
fun <T> frequencyMap(iterable: Iterable<T>): Map<T, Int> {
    return iterable.transformToMap { iterable.countElement(it) }
}

/**
 * Checks whether a string corresponds to a number
 */
fun String.isNumber(): Boolean {
    return all { it.isDigit() } || startsWith('-') && substring(1).all { it.isDigit() }
}

/**
 * Takes an iterable to a map where the keys are the elements of the iterable and
 * the values are the result of applying the transform function to the keys
 *
 * @param transform: the transform function
 */
fun <K, V> Iterable<K>.transformToMap(transform: (K) -> V): Map<K, V> = buildMap {
    for (i in this@transformToMap) {
        putIfAbsent(i, transform(i))
    }
}

/**
 * Counts how many times an element is repeated in an iterable
 *
 * @param element: the element
 */
fun <E> Iterable<E>.countElement(element: E): Int {
    return count { it == element }
}
