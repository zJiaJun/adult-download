package com.github.zjiajun.adult.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhujiajun
 * 16/6/26 20:52
 *
 * 单例 线程池工具类
 */
public class ThreadPoolTool {

    private static final Map<String,ExecutorService> POOL_CACHE = new HashMap<>();

    private ThreadPoolTool(){
    }

    private static class SingletonHolder {
        private static ThreadPoolTool instance = new ThreadPoolTool();
    }

    public static ThreadPoolTool getInstance() {
        return SingletonHolder.instance;
    }

    public ExecutorService getExecutor(String key) {
        return getExecutor(key,1);
    }

    public ExecutorService getExecutor(String key,int poolSize) {
        ExecutorService executorService = POOL_CACHE.get(key);
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(poolSize);
            POOL_CACHE.put(key,executorService);
        }
        return executorService;

    }



}
