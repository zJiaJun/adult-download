package com.github.zjiajun.adult.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public class DefaultManager implements Manager {

    private final BlockingQueue<String> blockingQueue;

    public DefaultManager() {
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
