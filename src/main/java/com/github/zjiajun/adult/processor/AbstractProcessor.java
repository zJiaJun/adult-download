package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.config.Config;
import com.github.zjiajun.adult.response.Response;
import com.github.zjiajun.adult.scheduler.Scheduler;
import com.github.zjiajun.adult.store.Store;
import com.github.zjiajun.adult.store.StoreResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/4/25
 */
public abstract class AbstractProcessor implements Processor {

    private final Scheduler scheduler;
    private final Store store;

    public AbstractProcessor(Scheduler scheduler, Store store) {
        this.scheduler = scheduler;
        this.store = store;
    }

    @Override
    public void process() {
        while (!Thread.currentThread().isInterrupted()) {
            Response response = scheduler.takeResponse();

            String content = response.getContent();

            Document document = Jsoup.parse(content, Config.getInstance().baseUrl());

            StoreResult storeResult = handler(document);

            if (storeResult.isSubUrl()) {
                storeResult.getSubRequestList().forEach(scheduler::putRequest);
            } else {
                store.store(storeResult);
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    protected abstract StoreResult handler(Document document);

}
