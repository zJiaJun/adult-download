package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.response.Response;
import com.github.zjiajun.adult.scheduler.Scheduler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/4/25
 */
public abstract class AbstractProcessor implements Processor {

    private final Scheduler scheduler;

    public AbstractProcessor(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void process() {
        while (!Thread.currentThread().isInterrupted()) {
            Response response = scheduler.takeResponse();

            String content = response.getContent();

            Document document = Jsoup.parse(content);

            handler(document);



            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    protected abstract void handler(Document document);
}
