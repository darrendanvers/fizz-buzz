package dev.codestijl.fizzbuzz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class to return the appropriate FizzBuzz instance based on the user's input. The FizzBuzz instance
 * returned is based on the following rules:
 *
 * <pre>
 * 1. If the number passed in is divisible by 3 and 5, an object that will return "FizzBuzz" is returned.
 * 2. If the number passed in is divisible by 3 but not 5, an object that will return "Fizz" is returned.
 * 3. If the number passed in is divisible by 5 but not 3, an object that will return "Buzz" is returned.
 * 4. If none of the above cases are true, and object that will return a string representation of the number
 *    is returned.
 * </pre>
 * @author Darren Danvers
 * @since  1.0.0
 */
public final class FizzBuzzFactory {

    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzFactory.class);
    private static final String LOG_MESSAGE = "Returning {} for {}.";

    private static final long FIZZ_DIVISOR = 3;
    private static final long BUZZ_DIVISOR = 5;

    private static final FizzImpl FIZZ = new FizzImpl();
    private static final BuzzImpl BUZZ = new BuzzImpl();
    private static final FizzBuzzImpl FIZZ_BUZZ = new FizzBuzzImpl();

    private FizzBuzzFactory() {
        // Intentionally empty.
    }

    /**
     * Returns a FizzBuzz appropriate for the supplied number.
     *
     * @param number The number to create a FizzBuzz instance for.
     * @return A FizzBuzz instance appropriate for number.
     */
    public static FizzBuzz get(final long number) {

        // Do this one first since if something is FizzBuzz, it, by definition, is Fizz
        // and is Buzz.
        if (isFizzBuzz(number)) {
            logger.debug(LOG_MESSAGE, FIZZ_BUZZ, number);
            return FIZZ_BUZZ;
        }

        // After isFizzBuzz, the other tests can be called in any order.
        if (isFizz(number)) {
            logger.debug(LOG_MESSAGE, FIZZ, number);
            return FIZZ;
        }

        if (isBuzz(number)) {
            logger.debug(LOG_MESSAGE, BUZZ, number);
            return BUZZ;
        }

        final FizzBuzz fizzBuzz = NumberImpl.of(number);
        logger.debug(LOG_MESSAGE, fizzBuzz, number);
        return fizzBuzz;
    }

    // Returns whether the number passed in is a Fizz and Buzz number.
    private static boolean isFizzBuzz(final long number) {
        return isFizz(number) && isBuzz(number);
    }

    // Returns whether the number passed in is a Fizz number.
    private static boolean isFizz(final long number) {
        return number % FIZZ_DIVISOR == 0;
    }

    // Returns whether the number passed in is a Buzz number.
    private static boolean isBuzz(final long number) {
        return number % BUZZ_DIVISOR == 0;
    }
}
