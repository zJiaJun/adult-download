package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.manager.Manager;
import com.github.zjiajun.adult.request.Request;

/**
 * @author zhujiajun
 * @since 2017/4/6
 */
public class DefaultScheduler implements Scheduler {

    private final Manager manager;

    public DefaultScheduler(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void schedule() {

        new Thread(() -> {
            Request url = manager.take();
            //如何把Request优雅的发送给Downloader


        }).start();



    }

}
