package com.github.zjiajun.adult.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by zhujiajun
 * 16/6/26 20:52
 *
 * 单例 线程池工具类
 */
public class ThreadPoolTool {

    private static final Map<String,ExecutorService> POOL_CACHE = new HashMap<>();

    private ThreadPoolTool() {}

    private static class SingletonHolder {
        private static ThreadPoolTool instance = new ThreadPoolTool();
    }

    public static ThreadPoolTool getInstance() {
        return SingletonHolder.instance;
    }

    public ExecutorService getExecutor(String key) {
        return getExecutor(key,1,100);
    }

    public ExecutorService getExecutor(String key,int poolSize,int queueSize) {
        return POOL_CACHE.computeIfAbsent(key, k -> new ThreadPoolExecutor(poolSize, poolSize,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy()));
    }

    public void shutDown(String key) {
        ExecutorService executorService = POOL_CACHE.get(key);
        Objects.requireNonNull(executorService);
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
