package com.github.zjiajun.adult.tool;


import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhujiajun
 * @since 2017/7/7
 */
public final class FileUtils {


    public static <T> T readLine(String file, LineProcessor<T> callback) {
        try {
            return Files.readLines(new File(file), StandardCharsets.UTF_8, callback);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void append(String content, String file) {
        try {
            Files.append(content,new File(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write(byte [] bytes, String file) {
        try {
            Files.write(bytes, new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
