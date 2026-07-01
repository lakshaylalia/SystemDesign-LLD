package org.example.core;

public interface LogAppender {
    // appends a log message to this appender's destination.
    void append(LogMessage message);

    // sets the minimum log level for this appender.
    void setLevel(LogLevel level);

    // gets the current minimum log level for this appender.
    LogLevel getLevel();

    // check if this appender is enabled for the given log level
    boolean isEnabled(LogLevel level);

    // sets the formatter for this appender.
    void setFormatter(LogFormatter logFormatter);

    // gets the current formatter for this appender.
    LogFormatter getFormatter();
}