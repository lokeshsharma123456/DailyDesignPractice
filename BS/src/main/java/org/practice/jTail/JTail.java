package org.practice.jTail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.RandomAccessFile;

public class JTail {
    private final String fileName;
    private final int nLines;

    public JTail(String fileName, int nLines) throws IllegalStateException {
        if(fileName == null || fileName.isEmpty() || nLines <= 0){
            throw new IllegalStateException(String.format("Invalid fileName: %s,  nLines: %s ", fileName, nLines));
        }
        this.fileName = fileName;
        this.nLines = nLines;
    }

    @Override
    public String toString() {
        return "JTail{" +
                "fileName='" + fileName + '\'' +
                ", nLines=" + nLines +
                '}';
    }

    public String tail() throws  IOException{
        try {
            final var filePtr = new RandomAccessFile(fileName, "r");
            final long fileSize = filePtr.length();
            filePtr.seek(fileSize - 1);

            long newLineCount = 0L;
            final var lastNlines = new StringBuilder();
            //read file in reverse and look for '\n'
            for(long i = fileSize - 1; i != -1; i--){
                filePtr.seek(i);
                final int readByte = filePtr.readByte();
                final char c = (char) readByte;

                if(c == '\n' || (System.lineSeparator().equals(String.valueOf(c)))){
                    ++newLineCount;
                    if(newLineCount > nLines)
                        break;
                }
                lastNlines.append(c);
            }
            lastNlines.reverse();
            return lastNlines.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
