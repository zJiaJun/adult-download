package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.manager.Manager;

/**
 * @author zhujiajun
 * @since 2017/4/6
 */
public class DefaultScheduler implements Scheduler {

    private final Manager manager;

    public DefaultScheduler(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void schedule() {
        manager.take();
    }

}
