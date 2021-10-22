package command;

import java.util.logging.Level;
import java.util.logging.LogManager;

public final class Logger {
    public static String GLOBAL_LOGGER_NAME = "LOGGER";
    public static java.util.logging.Logger myLogger;
    private static LogManager logManager;

    public Logger() {
        myLogger = java.util.logging.Logger.getLogger(GLOBAL_LOGGER_NAME);
        logManager = LogManager.getLogManager();
        logManager.getLogger(GLOBAL_LOGGER_NAME).setLevel(Level.SEVERE);
    }

}
