package me.repocord.server_manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger {
    private Logger() {}

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(String message) {
        String time = formatter.format(LocalDateTime.now());
        System.out.println("\033[34m[INFO " + time + "] " + message + "\033[32m");
    }

    public static void error(String message) {
        String time = formatter.format(LocalDateTime.now());
        System.out.println("\033[31m[ERROR " + time + "] " + message + "\033[32m");
    }
}
