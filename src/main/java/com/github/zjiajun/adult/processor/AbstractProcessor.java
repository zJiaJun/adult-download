package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.request.Method;
import com.github.zjiajun.adult.request.Request;
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

            //是否存在子Url
            if (!isExistsUrl(document.baseUri())) {
                document.select(urlCssQuery()).forEach(e -> {
                    String detailUrl = e.absUrl("href");
                    Request request = new Request.Builder().url(detailUrl).method(Method.GET).build();
                    scheduler.putRequest(request);
                });
            } else {

            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    protected abstract String urlCssQuery();

    protected abstract boolean isExistsUrl(String url);

}
