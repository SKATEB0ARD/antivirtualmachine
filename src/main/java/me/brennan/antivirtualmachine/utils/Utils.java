package me.brennan.antivirtualmachine.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class Utils {

    public static boolean searchSig(File file, byte[] badBytes) throws Exception {
        if (file.exists()) {
            if(file.isDirectory()) {
                if(file.canRead()) {
                    for(File subFiles : file.listFiles()) {
                        searchSig(subFiles, badBytes);
                    }
                }
            } else if(file.isFile()) {
                if(file.canRead()) {
                    final byte[] fileBytes = Files.readAllBytes(file.toPath());
                    for (int i = 0; i <= fileBytes.length - badBytes.length; i++) {
                        if(!(badBytes.length + i > fileBytes.length)) {
                            for(int i2 = 0; i2 < badBytes.length; i2++) {
                                if(badBytes[i2] == fileBytes[i2 + i])
                                    return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean isProcessRunning(String processName) throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder("tasklist.exe");
        final Process process = processBuilder.start();
        final String tasksList = toString(process.getInputStream());

        return tasksList.contains(processName);
    }

    private static String toString(InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        final String string = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        return string;
    }
}
