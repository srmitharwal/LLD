package com.personal.designPattern.cor.logger;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int logLevel, AbstractLogger abstractLogger) {
        this.logLevel = logLevel;
        this.setNextLogger(abstractLogger);
    }

    @Override
    public void log(int logLevel, String msg) {
        if (this.logLevel <= logLevel) {
            System.out.println(msg);
        }

        if (nextLogger != null) {
            nextLogger.log(logLevel, msg);
        }
    }
}
