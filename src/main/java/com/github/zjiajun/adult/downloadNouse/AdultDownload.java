package com.github.zjiajun.adult.downloadNouse;

import com.github.zjiajun.adult.exception.AdultException;
import com.github.zjiajun.adult.tool.LoggerTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zhujiajun
 * 16/6/27 14:38
 *
 * 下载工具类
 */
public final class AdultDownload {


    private AdultDownload() {}

    public static void down2File(DownloadRequest request) {
        down2File(request,null);
    }

    private static String getRequestCookieString(Map<String,String> cookies) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            if (!first)
                sb.append("; ");
            else
                first = false;
            sb.append(cookie.getKey()).append('=').append(cookie.getValue());
        }
        return sb.toString();
    }

    public static void down2File(DownloadRequest request, DownloadListener listener) {
        Objects.requireNonNull(request);
        FileOutputStream fileOutputStream = null;
        try {
            File path = new File(request.getFilePath());
            if (!path.exists()) path.mkdirs();
            HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
            connection.addRequestProperty("User-Agent", request.getUserAgent());
            if (request.getHeaders() != null && request.getHeaders().size() > 0)
                request.getHeaders().forEach(connection::addRequestProperty);

            if (request.getCookies() != null && request.getCookies().size() > 0)
                connection.addRequestProperty("Cookie",getRequestCookieString(request.getCookies()));

            connection.connect();
            InputStream inputStream = connection.getInputStream();
            fileOutputStream = new FileOutputStream(request.getFilePath() + request.getFileName());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            if (listener != null) listener.success();
        } catch (IOException e) {
            if (listener != null) listener.failure(request,e);
            throw new AdultException(LoggerTool.getTrace(e));
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
