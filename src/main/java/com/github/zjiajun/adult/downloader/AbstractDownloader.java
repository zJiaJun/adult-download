package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.scheduler.Scheduler;
import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.response.Response;

import java.io.IOException;
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

    protected abstract void afterDownload(Response response);

    @Override
    public void download() {
        while (true) {
            Request request = scheduler.take();
            beforeDownload(request);
            try {
                Response response = retrofitClient.execute(request);

                afterDownload(response);

                TimeUnit.SECONDS.sleep(3);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
