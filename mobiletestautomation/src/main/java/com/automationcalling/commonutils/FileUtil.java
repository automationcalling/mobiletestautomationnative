package com.automationcalling.commonutils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static void deleteFile(String filePath) throws IOException {
       Files.deleteIfExists(Paths.get(filePath));
    }

    public static void flushDirectory(File directoryName) throws IOException {
        FileUtils.cleanDirectory(directoryName);
    }

}
