package com.github.zjiajun.adult.tool;

import com.github.zjiajun.adult.exception.AdultException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Created by zhujiajun
 * 16/6/27 14:38
 *
 * 下载工具类
 */
public final class AdultDownload {


    public static void down2File(String url, String filePath) {
        Objects.requireNonNull(url);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        } catch (IOException e) {
            throw new AdultException("Download exception " + e.getMessage());
        }

    }

}
