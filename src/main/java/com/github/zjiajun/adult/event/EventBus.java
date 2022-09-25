package com.github.zjiajun.adult.event;

import com.github.zjiajun.adult.event.listeners.SexInSexListener;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/14 23:26
 */
public final class EventBus {

    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(20, 20,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("event-worker-thread-%d").setPriority(Thread.NORM_PRIORITY)
                    .setDaemon(false).build());

    private static final com.google.common.eventbus.EventBus INSTANCE = new com.google.common.eventbus.EventBus();

    public static void register(Object object) {
        INSTANCE.register(object);
    }

    public static void post(Object event) {
        INSTANCE.post(event);
    }

    public static void init() {
        EventBus.register(new SexInSexListener());
    }

}
