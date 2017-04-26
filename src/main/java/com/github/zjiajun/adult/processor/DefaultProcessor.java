package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.scheduler.Scheduler;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @since 2017/4/19
 */
public class DefaultProcessor extends AbstractProcessor {

    public DefaultProcessor(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected void handler(Document document) {

    }


}
