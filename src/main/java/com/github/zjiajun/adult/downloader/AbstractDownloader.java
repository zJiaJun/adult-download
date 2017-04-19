package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.manager.Scheduler;
import com.github.zjiajun.adult.request.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

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

    protected abstract boolean isNextUrl(Request request);

    @Override
    public void download() {
        while (true) {
            Request request = scheduler.take();
            beforeDownload(request);
            ResponseBody responseBody;
            try {

                String originalHtml = new String(responseBody.bytes(), Charset.forName("GBK"));
                afterDownload(originalHtml);
                TimeUnit.SECONDS.sleep(3);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
