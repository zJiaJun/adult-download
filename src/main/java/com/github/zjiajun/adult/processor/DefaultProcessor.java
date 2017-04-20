package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.response.Response;
import com.github.zjiajun.adult.scheduler.Scheduler;

/**
 * @author zhujiajun
 * @since 2017/4/19
 */
public class DefaultProcessor implements Processor {

    private final Scheduler scheduler;

    public DefaultProcessor(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void process() {
        while (true) {
            Response response = scheduler.takeResponse();

        }

    }


}
