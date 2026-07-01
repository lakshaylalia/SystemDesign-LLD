package org.example.core;

public interface LogFilter {

    boolean shouldLog(LogMessage message);

    void setLevel(LogLevel level);

    LogLevel getLevel();
}
