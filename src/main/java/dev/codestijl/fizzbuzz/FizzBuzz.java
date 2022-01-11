package dev.codestijl.fizzbuzz;

/**
 * Base class for objects that return a FizzBuzz value.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
/* default */ abstract class FizzBuzz {

    /**
     * Returns the FizzBuzz value to print.
     *
     * @return The FizzBuzzValue to print.
     */
    protected abstract String get();

    @Override
    public String toString() {
        return String.format("{%s}", this.get());
    }
}
