package com.github.zjiajun.adult.tool;


import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/7/7
 */
public final class FileUtils {



    public static void write(byte [] bytes, String file) {
        try {
            Files.write(bytes, new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
