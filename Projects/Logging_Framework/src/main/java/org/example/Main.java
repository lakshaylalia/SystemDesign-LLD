package org.example;

import org.example.appenders.*;
import org.example.formatters.*;
import org.example.filter.*;
import org.example.core.*;

public class Main {
    static void main(String[] args) {
        System.out.println("=== LoggingFramework Demo ===\n");

        // Basic logging with different levels
         demoBasicLogging();

        // Multiple appenders
        // demoMultipleAppenders();

        // Custom formatters
        // demoCustomFormatters();

        // Filters
        // demoFilters();

        // Thread safety
        // demoThreadSafety();

        System.out.println("\n=== Demo Complete ===");
    }

    private static void demoBasicLogging() {
        System.out.println("1. Basic Logging Demo:");
        System.out.println("----------------------");

        Logger logger = new LoggerImpl("BasicLogger");

        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warning("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");

        System.out.println();
    }

    private static void demoMultipleAppenders() {
        System.out.println("2. Multiple Appenders Demo:");
        System.out.println("---------------------------");

        Logger logger = new LoggerImpl("MultiAppenderLogger");

        FileAppender fileAppender = new FileAppender("demo.log");
        logger.addAppender(fileAppender);

        logger.info("This message goes to both console and file");
        logger.error("This error also goes to both destinations");

        System.out.println("Check 'demo.log' file for the logged messages");
        System.out.println();
    }

    private static void demoCustomFormatters() {
        System.out.println("3. Custom Formatters Demo:");
        System.out.println("--------------------------");

        Logger logger = new LoggerImpl("FormatterLogger");

        SimpleFormatter customFormatter = new SimpleFormatter("[%LEVEL] %TIMESTAMP - %MESSAGE");
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setFormatter(customFormatter);

        logger.addAppender(consoleAppender);

        logger.info("This message uses custom formatting");
        logger.error("This error also uses custom formatting");

        System.out.println();
    }

    private static void demoFilters() {
        System.out.println("4. Filters Demo:");
        System.out.println("----------------");

        Logger logger = new LoggerImpl("FilterLogger");

        LevelFilter levelFilter = new LevelFilter(LogLevel.WARNING);
        logger.addFilter(levelFilter);

        logger.debug("This debug message will be filtered out");
        logger.info("This info message will be filtered out");
        logger.warning("This warning message will be shown");
        logger.error("This error message will be shown");

        System.out.println();
    }

    private static void demoThreadSafety() {
        System.out.println("5. Thread Safety Demo:");
        System.out.println("----------------------");

        Logger logger = new LoggerImpl("ThreadSafeLogger");

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    logger.info("Thread " + threadId + " - Message " + j);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All threads completed - check for any mixed-up messages above");
        System.out.println();
    }
}
