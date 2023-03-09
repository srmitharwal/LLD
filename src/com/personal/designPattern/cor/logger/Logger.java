package com.personal.designPattern.cor.logger;

public class Logger {
    private static Logger logger = null;
    private static AbstractLogger abstractLogger;
    private Logger () {

    }

    public static Logger getLogger (){
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                    init();
                }
            }
        }
        return logger;
    }

    private static void init() {
        AbstractLogger debugLogger = new DebugLogger(3, null);
        AbstractLogger errorLogger = new ErrorLogger(2, debugLogger);
        AbstractLogger infoLogger = new InfoLogger(1, errorLogger);
        abstractLogger = infoLogger;
    }

    private void log(int level, String msg) {
        abstractLogger.log(level, msg);
    }

    public void info(String msg) {
        log(1, msg);
    }

    public void error(String msg) {
        log(2, msg);
    }

    public void debug(String msg) {
        log(3, msg);
    }

}
