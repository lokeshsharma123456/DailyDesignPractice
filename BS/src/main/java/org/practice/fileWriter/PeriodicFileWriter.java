package org.practice.fileWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;

public class PeriodicFileWriter implements Runnable{
    private final Path filePath;
    private final AtomicInteger counter = new AtomicInteger();
    public PeriodicFileWriter(Path finalPath) {
        this.filePath = finalPath;
        try{
            Files.write(filePath, String.format("This is a header line%n").getBytes(StandardCharsets.UTF_8));
        } catch (final IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        final String str = String.format("This is %d line. %n", counter.addAndGet(1));
        final byte[]  bytesToWrite = str.getBytes(StandardCharsets.UTF_8);

        //append mode
        try{
            Files.write(filePath, bytesToWrite, StandardOpenOption.APPEND);
        }catch (final IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
