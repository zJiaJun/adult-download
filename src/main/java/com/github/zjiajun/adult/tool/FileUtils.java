package com.github.zjiajun.adult.tool;


import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/7/7
 */
public final class FileUtils {


    public static void touch(String file) {
        try {
            File f = new File(file);
            Files.createParentDirs(f);
            Files.touch(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLine(String file) {
        try {
            return Files.readLines(new File(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static void append(String content, String file) {
        try {
            Files.asCharSink(new File(file), StandardCharsets.UTF_8, FileWriteMode.APPEND).write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write(byte [] bytes, String file) {
        try {
            File f = new File(file);
            Files.createParentDirs(f);
            touch(file);
            Files.write(bytes, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
