package com.personal.designPattern.cor.logger;

public class DebugLogger extends AbstractLogger {

    public DebugLogger (int logLevel, AbstractLogger logger) {
        this.logLevel = logLevel;
        this.setNextLogger(logger);
    }

    @Override
    public void log(int logLevel, String msg) {
        if (this.logLevel <= logLevel) {
            System.out.println(msg);
        }

        if(nextLogger != null) {
            nextLogger.log(logLevel, msg);
        }
    }
}
