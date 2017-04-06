package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.request.Request;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public interface Scheduler {

    void put(Request request);

    Request take();

}
