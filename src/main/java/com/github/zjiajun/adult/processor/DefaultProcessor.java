package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.response.Response;
import com.github.zjiajun.adult.scheduler.Scheduler;

import java.util.concurrent.TimeUnit;

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

            String content = response.getContent();


            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
