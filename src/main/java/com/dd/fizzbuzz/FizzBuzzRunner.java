package com.dd.fizzbuzz;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver class that handles running the FizzBuzz routine.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
public final class FizzBuzzRunner {

    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzRunner.class);

    private FizzBuzzRunner() {
        // Intentionally empty.
    }

    /**
     * Runs the FizzBuzz routine and returns a stream of the results.
     *
     * @param maxValue The maximum value to include in the list of FizzBuzz results.
     * @return A stream of FizzBuzz results.
     * @throws IllegalArgumentException If the value passed in is not greater than one.
     */
    public static Stream<String> streamOf(final long minValue, final long maxValue) {

        if (minValue >= maxValue) {
            throw new IllegalArgumentException(String.format("Minimum value must be greater than the maximum. %d and %d provided.",
                    minValue, maxValue));
        }

        logger.debug("Producing FizzBuzz stream from {} to {}.", minValue, maxValue);

        // Produce a stream of longs from the low to the high values supplied.
        // Map the long to a class that implements FizzBuzz.
        // Have that class produce a String.
        return LongStream.rangeClosed(minValue, maxValue)
                .mapToObj(FizzBuzzFactory::get)
                .map(FizzBuzz::get);
    }
}
