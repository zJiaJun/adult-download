package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.request.Method;
import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.scheduler.Scheduler;
import com.github.zjiajun.adult.store.Store;
import com.github.zjiajun.adult.store.StoreResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author zhujiajun
 * @since 2017/4/19
 */
public class DefaultProcessor extends AbstractProcessor {

    public DefaultProcessor(Scheduler scheduler, Store store) {
        this(scheduler, 1,store);
    }

    public DefaultProcessor(Scheduler scheduler, int processThreadCount,Store store) {
        super(scheduler,store);
        new Thread(DefaultProcessor.super::process, "adult-process-thread").start();
    }


    @Override
    protected StoreResult handler(Document document) {
        StoreResult.Builder storeBuilder = new StoreResult.Builder();
        Elements elements = document.select("table[id^=forum]:contains(版块主题) span a");
        if (elements.isEmpty()) {
            Elements imgElements = document.select("div.t_msgfont img[src^=http]");
            if (!imgElements.isEmpty() && null != imgElements.first()) {
                String imgUrl = imgElements.first().attr("src");
                String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                storeBuilder.result(imgFileName, imgUrl);
            }

            Elements attachElements = document.select("dl.t_attachlist a[href^=attachment]");
            if (!attachElements.isEmpty()) {
                Element element = attachElements.first();
                if (null != element) {
                    String attachName = element.text();
                    String attachUrl = element.absUrl("href");
                    storeBuilder.result(attachName, attachUrl);
                }
            }
        } else {
            elements.forEach(e -> {
                String detailUrl = e.absUrl("href");
                Request subRequest = new Request.Builder().url(detailUrl).method(Method.GET).build();
                storeBuilder.subUrl(subRequest);
            });
        }
        return storeBuilder.build();

    }


}
