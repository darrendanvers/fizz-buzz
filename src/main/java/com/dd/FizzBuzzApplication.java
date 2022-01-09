package com.dd;

import com.dd.fizzbuzz.FizzBuzzRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver for the FizzBuzz application.
 *
 * @author Darren Danvers
 * @since 1.0.0
 */
// This is not a J2EE app, so ignore this error here. I do not, however, want
// any other place to exit, so not turning it off globally.
@SuppressWarnings({"PMD.DoNotCallSystemExit", "PMD.SystemPrintln"})
public final class FizzBuzzApplication {

    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzApplication.class);

    /**
     * Called by the OS to kick this job off.
     *
     * @param args The command line arguments. Should include one whole value greater than one.
     */
    public static void main(final String[] args) {

        try {

            final Configuration configuration = Configuration.builder().parseCommandLine(args);

            if (configuration.isDebugEnabled()) {
                LoggerUtils.setRootLogToDebug();
            }

            if (configuration.isPrintUsage()) {
                usage();
                return;
            }

            // Process FizzBuzz up to the number they passed and print the result to standard out.
            FizzBuzzRunner.streamOf(configuration.getStart(), configuration.getEnd())
                    .forEach(logger::info);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getLocalizedMessage());
            usage();
        }
    }

    // Prints the application usage and exits with an error.
    private static void usage() {

        System.err.println("usage: [-s start] [-e end] [-d] [-h]");
        System.err.println("Where:");
        System.err.println("start is the fist number (inclusive) to include in the Fizz Buzz list.");
        System.err.println("end is the last number (inclusive) to include in the Fizz Buzz list.");
        System.err.println("-d turns on debug logging.");
        System.err.println("-h prints this message and exist.");
        System.exit(-1);
    }

    private FizzBuzzApplication() {
        // Intentionally empty.
    }
}
