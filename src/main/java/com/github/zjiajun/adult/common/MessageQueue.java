package com.github.zjiajun.adult.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhujiajun
 * @since 2017/2/6
 */
public final class MessageQueue {

    private static final BlockingQueue<Message> QUEUE = new ArrayBlockingQueue<>(1000);

    public static void put(Message message) {
        try {
            QUEUE.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Message take() {
        Message msg = null;
        try {
            msg = QUEUE.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
