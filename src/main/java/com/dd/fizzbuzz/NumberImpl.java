package com.dd.fizzbuzz;

/**
 * Handles instances where a number should be printed.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
/* default */ final class NumberImpl extends FizzBuzz {

    private final String numberAsString;

    private NumberImpl(final long number) {
        super();
        this.numberAsString = String.format("%,d", number);
    }

    /**
     * Returns a new NumberImpl.
     *
     * @param number The number that should be printed.
     */
    public static NumberImpl of(final long number) {
        return new NumberImpl(number);
    }

    @Override
    public String get() {
        return this.numberAsString;
    }
}
