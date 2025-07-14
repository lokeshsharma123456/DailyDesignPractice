package org.designPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileWatcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello, World!");
        File file = new File("log.txt");
        new Thread(()->{
            try{
                LogWriter.KeepWriting(file);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        long filePointer = file.length();
        while(true){
            long fileSizeInBytes = file.length();
            if(filePointer < fileSizeInBytes){
                //something is appended
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.seek(filePointer);

                String line;
                while ((line = raf.readLine()) != null){
                    System.out.println(line);
                }
                filePointer = raf.getFilePointer();
                raf.close();
            }
            Thread.sleep(1000);
        }
    }

}
