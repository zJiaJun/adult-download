package com.github.zjiajun.adult.event;

import com.github.zjiajun.adult.event.listeners.AdultListener;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/14 23:26
 */
@Slf4j
public final class EventBus {

    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(20, 20,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("event-worker-thread-%d").setPriority(Thread.NORM_PRIORITY)
                    .setDaemon(false).build());

    private static final com.google.common.eventbus.EventBus INSTANCE =
            new com.google.common.eventbus.AsyncEventBus(executorService, new ExceptionHandler());

    public static void register(Object object) {
        INSTANCE.register(object);
    }

    public static void post(Object event) {
        INSTANCE.post(event);
    }

    public static void init() {
        EventBus.register(new AdultListener());
    }

    private static class ExceptionHandler implements SubscriberExceptionHandler {

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            log.error(message(context), exception);
        }

        private static String message(SubscriberExceptionContext context) {
            Method method = context.getSubscriberMethod();
            return "Exception thrown by subscriber method "
                    + method.getName()
                    + '('
                    + method.getParameterTypes()[0].getName()
                    + ')'
                    + " on subscriber "
                    + context.getSubscriber()
                    + " when dispatching event: "
                    + context.getEvent();
        }
    }

}
