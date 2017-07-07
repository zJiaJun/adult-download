package com.github.zjiajun.adult.tool;


import com.github.zjiajun.adult.config.Config;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/7/7
 */
public final class FileUtils {


    private static final String DOWNLOAD_PATH = Config.getInstance().downloadPath();

    public static void write(byte [] bytes, String fileName) {
        try {
            Files.write(bytes, new File(DOWNLOAD_PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
