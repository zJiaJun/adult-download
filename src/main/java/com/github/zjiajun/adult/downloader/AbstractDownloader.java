package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.manager.Scheduler;
import com.github.zjiajun.adult.request.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class AbstractDownloader implements Downloader {

    private final Scheduler scheduler;
    private final RetrofitClient retrofitClient;

    public AbstractDownloader(Scheduler scheduler) {
        this.scheduler = scheduler;
        retrofitClient = RetrofitClient.getInstance();
    }

    protected abstract void beforeDownload(Request request);

    protected abstract void afterDownload(Object response);

    @Override
    public void download() {
        while (true) {
            Request request = scheduler.take();
            beforeDownload(request);
            ResponseBody responseBody;
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
                afterDownload(originalHtml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
