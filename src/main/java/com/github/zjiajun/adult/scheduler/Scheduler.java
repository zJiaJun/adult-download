package com.github.zjiajun.adult.scheduler;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public interface Scheduler {

    void put(String url);

    String take();

}
