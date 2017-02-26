package com.github.zjiajun.adult.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhujiajun
 * 16/6/26 20:52
 *
 * 单例 线程池工具类
 */
public class ThreadPoolTool {

    private static final Map<String, ExecutorService> POOL_CACHE = new HashMap<>();

    private ThreadPoolTool() {}

    private static class SingletonHolder {
        private static ThreadPoolTool instance = new ThreadPoolTool();
    }

    public static ThreadPoolTool getInstance() {
        return SingletonHolder.instance;
    }

    public ExecutorService getSingleExecutor(String key) {
        return getExecutor(key,1, new LinkedBlockingDeque<>());
    }

    public ExecutorService getExecutor(String key, int poolSize, BlockingQueue<Runnable> blockingQueue) {
        return POOL_CACHE.computeIfAbsent(key, k -> new ThreadPoolExecutor(poolSize, poolSize,
                0L, TimeUnit.SECONDS, blockingQueue, new ThreadFactory() {

            private final AtomicInteger cnt = new AtomicInteger(1);
            private final String threadName = "pool-adult-[%s]-%d";

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, String.format(threadName, key, cnt.getAndIncrement()));
                thread.setPriority(Thread.NORM_PRIORITY);
                return thread;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy()));
    }

    public ExecutorService getExecutor(String key, int poolSize, int queueSize) {
        return getExecutor(key, poolSize, new ArrayBlockingQueue<>(queueSize));
    }

    public void shutDown(String key) {
        ExecutorService executorService = POOL_CACHE.get(key);
        Objects.requireNonNull(executorService);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

}
