package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.response.Response;
import com.github.zjiajun.adult.scheduler.Scheduler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
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

            List<Request> requestList = handler(document);

            if (!requestList.isEmpty()) {
                requestList.forEach(scheduler::putRequest);
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    protected abstract List<Request> handler(Document document);

}
