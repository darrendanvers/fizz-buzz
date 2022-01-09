package com.dd.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests FizzBuzzRunner.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
public class FizzBuzzRunnerTest {

    /**
     * Utility to help test the stream of FizzBuzzes.
     *
     * @author Darren Danvers
     * @since 1.0.0
     */
    private static final class FizzBuzzValidator implements AutoCloseable {

        final long start;
        final long end;
        long current;
        long nextExpected;

        private FizzBuzzValidator(final long start, final long end) {
            this.start = start;
            this.nextExpected = start;
            this.end = end;
        }

        private void validate(String fizzBuzz) {

            // Keep track of this so that  we can check we got the expected last value.
            this.current = this.nextExpected;

            Assert.assertEquals(getFizzBuzzNumber(this.nextExpected), fizzBuzz);
            this.nextExpected++;
        }

        private String getFizzBuzzNumber(final long number) {

            if (number % 3 == 0 && number % 5 == 0) {
                return "FizzBuzz";
            }

            if (number % 3 == 0) {
                return "Fizz";
            }

            if (number % 5 == 0) {
                return "Buzz";
            }

            return String.format("%,d", number);
        }

        @Override
        public void close() {
            Assert.assertEquals(String.format("Expected end at %d, but was %d.", this.end, this.current), this.end, current);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void streamOf_minLessThanMax_throwsError() {

        FizzBuzzRunner.streamOf(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void streamOf_minEqualMax_throwsError() {

       FizzBuzzRunner.streamOf(100, 100);
    }

    @Test
    public void streamOf_tenThousandPositives_handles() throws Exception {

        try (FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator(1, 10_000)) {
            FizzBuzzRunner.streamOf(1, 10_000).forEach(fizzBuzzValidator::validate);
        }

    }

    @Test
    public void streamOf_negativeToPositive_works() throws Exception {

        try (FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator(-10_000, 10_100)) {
            FizzBuzzRunner.streamOf(-10_000, 10_100).forEach(fizzBuzzValidator::validate);
        }
    }

    @Test
    public void streamOf_allNegative_works() throws Exception {

        try (FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator(-10_000, -1)) {
            FizzBuzzRunner.streamOf(-10_000, -1).forEach(fizzBuzzValidator::validate);
        }
    }

    @Test
    public void streamOf_extremeNegative_works() throws Exception {

        try (FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator(Long.MIN_VALUE, Long.MIN_VALUE + 1_000)) {
            FizzBuzzRunner.streamOf(Long.MIN_VALUE, Long.MIN_VALUE + 1_000).forEach(fizzBuzzValidator::validate);
        }
    }

    @Test
    public void streamOf_extremePositive_works() throws Exception {

        try (FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator(Long.MAX_VALUE - 1_000, Long.MAX_VALUE)) {
            FizzBuzzRunner.streamOf(Long.MAX_VALUE - 1_000, Long.MAX_VALUE).forEach(fizzBuzzValidator::validate);
        }
    }
}
