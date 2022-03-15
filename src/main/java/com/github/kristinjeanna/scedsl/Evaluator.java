package com.github.kristinjeanna.scedsl;

import java.util.Collection;

/**
 * Provides the interface for the evaluators defined in this library.
 */
public interface Evaluator {

    /**
     * Evaluates a string collection against the expression represented by this
     * {@code Evaluator} instance.
     *
     * @param stringCollection the string collection to use
     * @return the boolean result of evaluating the collection
     */
    boolean evaluate(Collection<String> stringCollection);
}
