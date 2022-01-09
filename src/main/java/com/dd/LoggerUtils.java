package com.dd;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;

/**
 * Used to do global log configuration.
 */
public final class LoggerUtils {

    /**
     * Sets the logger to allow debug log messages.
     */
    public static void setRootLogToDebug() {
        final ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)
                LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.DEBUG);
    }

    private LoggerUtils() {

        // Intentionally empty.
    }
}
