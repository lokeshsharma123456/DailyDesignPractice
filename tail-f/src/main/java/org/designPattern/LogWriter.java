package org.designPattern;

import java.io.BufferedWriter;
import java.io.*;
import java.io.FileWriter;

public class LogWriter {
    public static void writeLog(File file, String msg) throws InterruptedException {
        try (FileWriter fw = new FileWriter(file, true); // true for append mode
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(msg);
//            System.out.println("Content appended successfully to " + file);

        } catch (IOException e) {
            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    public static void KeepWriting(File file) throws InterruptedException {
        int counter = 0;
        while (true) {
            writeLog(file, "Sample log in iterative way increasing at line #" + ++counter + "\n");
            Thread.sleep(3000);
        }
    }


}
