package application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerController {

    // singleton reference
    private static LoggerController instance = new LoggerController();

    // gets the current singleton instance
    public static LoggerController getLoggerInstance(){
        return instance;
    }

    Logger curFileLogger;

    // constructs the dbHandler object
    private LoggerController(){
        LoggerControllerInitialization();
    }

    void LoggerControllerInitialization(){
        try {
            FileHandler fileHandler = new FileHandler("logfile.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);

            curFileLogger = logger;

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with the Logger.");
        }
    }

    void logWarning(String error){
        curFileLogger.warning(error);
    }

    void logInfo(String info){
        curFileLogger.info(info);
    }
}
