package com.github.zjiajun.adult;

import com.github.zjiajun.adult.downloader.DefaultDownloader;
import com.github.zjiajun.adult.downloader.Downloader;
import com.github.zjiajun.adult.processor.DefaultProcessor;
import com.github.zjiajun.adult.processor.Processor;
import com.github.zjiajun.adult.scheduler.DefaultScheduler;
import com.github.zjiajun.adult.scheduler.Scheduler;
import com.github.zjiajun.adult.request.LoginParamBuild;
import com.github.zjiajun.adult.request.Method;
import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.store.DefaultStore;
import com.github.zjiajun.adult.store.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    public Adult () {}

    private Request loginRequest;
    private List<Request> requests = new ArrayList<>();
    private Scheduler scheduler;
    private Downloader downloader;
    private Processor processor;
    private Store store;

    public Adult login(String loginUrl, LoginParamBuild loginParamBuild) {
        Map<String,String> param = new HashMap<>(50);
        loginParamBuild.param(param);
        this.loginRequest = new Request.Builder().url(loginUrl).charset("GBK").login().data(param).method(Method.POST).build();
        return this;
    }


    public Adult url(String url) {
        requests.add(new Request.Builder().url(url).charset("GBK").build());
        return this;
    }


    public void start() {
        init();

        if (null != loginRequest) {
            scheduler.putRequest(loginRequest);
        }
        if (null != requests && !requests.isEmpty()) {
            requests.forEach(scheduler::putRequest);
        }

    }


    private void init() {
        if (null == scheduler) {
            scheduler = new DefaultScheduler();
        }
        if (null == downloader) {
            downloader = new DefaultDownloader(scheduler);
        }
        if (null == store) {
            store = new DefaultStore();
        }
        if (null == processor) {
            processor = new DefaultProcessor(scheduler, store);
        }
    }




}
