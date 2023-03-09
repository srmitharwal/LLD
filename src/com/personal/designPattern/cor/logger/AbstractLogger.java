package com.personal.designPattern.cor.logger;

public abstract class AbstractLogger {
    int logLevel;

    AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public abstract void log(int logLevel, String msg);

}
