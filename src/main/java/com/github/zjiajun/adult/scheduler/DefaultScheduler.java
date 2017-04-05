package com.github.zjiajun.adult.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public class DefaultScheduler implements Scheduler {

    private final BlockingQueue<String> blockingQueue;

    public DefaultScheduler() {
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void put(String url) {
        try {
            blockingQueue.put(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String take() {
        String url = null;
        try {
            url = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return url;
    }
}
