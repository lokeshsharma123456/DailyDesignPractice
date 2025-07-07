package org.practice.fileWriterTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.practice.fileWriter.PeriodicFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PeriodicFileWriterTest {

    @Test
    @DisplayName("Test writing to a file periodically")
    void testWritingToFilePeriodically() throws InterruptedException, IOException {
        final var path = Path.of("src", "test", "resources", "realtime.log");
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        Files.createFile(path);
        final var fileWriter = new PeriodicFileWriter(path);
        final var executor = new ScheduledThreadPoolExecutor(1);
        final long timeUnits = 500L;
        executor.scheduleAtFixedRate(fileWriter, timeUnits,timeUnits, TimeUnit.MILLISECONDS);
        TimeUnit.MILLISECONDS.sleep(timeUnits*10);;
    }
}
