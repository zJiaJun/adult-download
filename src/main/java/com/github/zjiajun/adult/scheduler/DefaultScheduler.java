package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.request.Request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public class DefaultScheduler implements Scheduler {

    private final BlockingQueue<Request> blockingQueue;

    public DefaultScheduler() {
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void put(Request request) {
        try {
            blockingQueue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Request take() {
        Request request = null;
        try {
            request = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }
}
