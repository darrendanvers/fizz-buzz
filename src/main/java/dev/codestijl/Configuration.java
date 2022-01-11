package dev.codestijl;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Stores the application configuration.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
public final class Configuration {

    private final boolean printUsage;
    private final boolean debugEnabled;
    private final long end;
    private final long start;

    /**
     * Returns a builder with which to construct a Configuration.
     *
     * @return A builder with which to construct a Configuration.
     */
    public static ConfigurationBuilder builder() {

        return new ConfigurationBuilder();
    }

    // Constructs a new Configuration object.
    private Configuration(final boolean printUsage, final boolean debugEnabled, final long start, final long end) {
        this.debugEnabled = debugEnabled;
        this.start = start;
        this.end = end;
        this.printUsage = printUsage;
    }

    /**
     * Returns whether the user wants to turn on debug level logging.
     *
     * @return True if the user wants debug logging and false otherwise.
     */
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * Returns the number (inclusive) the user wants to print FizzBuzz numbers to.
     *
     * @return The number (inclusive) the user wants to print FizzBuzz numbers to.
     */
    public long getEnd() {
        return this.end;
    }

    /**
     * Returns the number (inclusive) the user wants to start printing FizzBuzz numbers from.
     *
     * @return The number (inclusive) the user wants to start printing FizzBuzz numbers from.
     */
    public long getStart() {
        return this.start;
    }

    /**
     * Returns whether the application should print usage and stop.
     *
     * @return True if the application should print usage and false otherwise.
     */
    public boolean isPrintUsage() {
        return printUsage;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "debugEnabled=" + debugEnabled +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    /**
     * Class used to build Configurations.
     */
    public static class ConfigurationBuilder {

        private static final String DEBUG_LOG_FLAG = "-d";
        private static final String START_FLAG = "-s";
        private static final String END_FLAG = "-e";
        private static final String USAGE_FLAG = "-h";

        // These are the properties that will be used to build a Configuration.
        private boolean debugEnabled;
        private long end = 100;
        private long start = 1;
        private boolean printUsage;

        // Keep track of the flags set so that they're only set once.
        private boolean debugSet;
        private boolean startSet;
        private boolean endSet;

        private ConfigurationBuilder() {

            // Intentionally empty.
        }

        /**
         * Takes a String array (the command line arguments), parses them, and returns a Configuration for the application
         * to use.
         *
         * @param args The command line arguments.
         * @return A configuration based on the command line arguments.
         * @throws IllegalArgumentException Any error in the user's command line setup will throw an Illegal argument exception.
         */
        public Configuration parseCommandLine(final String... args) {

            int index = 0;
            while (index < args.length) {

                // If they say print usage, ignore other arguments.
                if (Objects.equals(args[index], USAGE_FLAG)) {
                    this.setUsageFlag();
                    break;
                }

                if (Objects.equals(args[index], DEBUG_LOG_FLAG)) {
                    index = this.consumeDebugFlag(index);
                    continue;
                }

                if (Objects.equals(args[index], START_FLAG)) {
                    index = this.consumeStartFlag(index, args);
                    continue;
                }

                if (Objects.equals(args[index], END_FLAG)) {
                    index = this.consumeEndFlag(index, args);
                    continue;
                }

                // Here, we have an unknown argument.
                throw new IllegalArgumentException(String.format("Unknown argument: %s.", args[index]));
            }

            return this.build();
        }

        /**
         * Constructs a new Configuration based on the parameters set in the builder.
         *
         * @return A new Configuration.
         * @throws IllegalArgumentException If the configuration is not valid.
         */
        public Configuration build() {

            if (this.start >= this.end) {
                throw new IllegalArgumentException(String.format("Start value must be less than the end value: %d, %d",
                        this.start, this.end));
            }

            return new Configuration(this.printUsage, this.debugEnabled, this.start, this.end);
        }

        /**
         * Sets the first number the user wants to generate FizzBuzz numbers for.
         *
         * @param arg The last number the user wants to generate FizzBuzz numbers for.
         */
        public void setStart(final long arg) {

            this.start = arg;
        }

        /**
         * Sets the last number the user wants to generate FizzBuzz numbers for.
         *
         * @param arg The last number the user wants to generate FizzBuzz numbers for.
         */
        public void setEnd(final long arg) {

            this.end = arg;
        }

        /**
         * Turns on the debug flag.
         */
        public void setDebugFlag() {

            this.debugEnabled = true;
        }

        /**
         * Turns on the usage flag.
         */
        public void setUsageFlag() {
            this.printUsage = true;
        }

        private int consumeDebugFlag(final int index) {
            if (this.debugSet) {
                throw new IllegalArgumentException("Debug flag can only be set once.");
            }
            this.debugSet = true;
            this.setDebugFlag();
            return index + 1;
        }

        private int consumeStartFlag(final int index, final String... args) {
            if (this.startSet) {
                throw new IllegalArgumentException("Start flag can only be set once.");
            }
            this.startSet = true;
            return consumeFlagAndValue(index, args, s -> this.setStart(argToLong(s)));
        }

        private int consumeEndFlag(final int index, final String... args) {
            if (this.endSet) {
                throw new IllegalArgumentException("End flag can only be set once.");
            }
            this.endSet = true;
            return consumeFlagAndValue(index, args, s -> this.setEnd(argToLong(s)));
        }

        // Convenience method that will process a flag and the value immediately following the value.
        // Pass in the location of the flag in the array, the array of values, and a function to
        // call passing in the immediate next value.
        // Returns the index in the array of the value it just read.
        private static int consumeFlagAndValue(final int index, final String[] args, final Consumer<String> consumer) {

            if (index >= args.length - 1) {
                throw new IllegalArgumentException("Missing value for argument.");
            }

            final int nextIndex = index + 1;
            consumer.accept(args[nextIndex]);
            return nextIndex + 1;
        }

        // Converts a string to a long.
        private static long argToLong(final String arg) {

            try {
                return Long.parseLong(arg);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format("'%s' cannot be converted to a number.", arg), e);
            }
        }
    }
}
