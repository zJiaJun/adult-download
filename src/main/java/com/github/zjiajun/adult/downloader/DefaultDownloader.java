package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.manager.Scheduler;
import com.github.zjiajun.adult.request.Request;

/**
 * @author zhujiajun
 * @since 2017/4/14
 */
public class DefaultDownloader extends AbstractDownloader {

    public DefaultDownloader(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected void beforeDownload(Request request) {

    }

    @Override
    protected void afterDownload(Object response) {

    }
}
