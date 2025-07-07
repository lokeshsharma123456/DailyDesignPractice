package org.designPattern;

import org.designPattern.AppLogger.AppLogger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        AppLogger app = AppLogger.getInstance();
        app.log("First meeting");
        AppLogger app1  = AppLogger.getInstance();
        app1.log("Currently second time");
    }
}