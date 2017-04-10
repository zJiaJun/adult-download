package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.request.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class AbstractDownloader implements Downloader {

    private final RetrofitClient retrofitClient;

    public AbstractDownloader() {
        retrofitClient = RetrofitClient.getInstance();
    }

    @Override
    public void download(Request request) {
        ResponseBody responseBody;
        Page page = new Page();
        try {
            switch (request.getMethod()) {
                case GET:
                    responseBody = retrofitClient.get(request.getUrl(), request.getData());
                    break;
                case POST:
                    responseBody = retrofitClient.post(request.getUrl(), request.getData());
                    break;
                default:
                    throw new RuntimeException();
            }
            String originalHtml = new String(responseBody.bytes(), Charset.forName("GBK"));
            page.setHtml(originalHtml);
            page.setRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
