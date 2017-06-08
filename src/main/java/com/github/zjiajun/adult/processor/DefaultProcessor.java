package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.request.Method;
import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.scheduler.Scheduler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/4/19
 */
public class DefaultProcessor extends AbstractProcessor {

    public DefaultProcessor(Scheduler scheduler) {
        super(scheduler);
    }


    @Override
    protected List<Request> handler(Document document) {
        List<Request> requestList = new ArrayList<>();

        String baseUri = document.baseUri();
        //详情页
        if (baseUri.lastIndexOf(".html") > 0) {
            Elements imgElements = document.select("div.t_msgfont img[src^=http]");
            if (!imgElements.isEmpty()) {
                String imgUrl = imgElements.first().attr("src");
                String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
            }

            Elements attachElements = document.select("dl.t_attachlist a[href^=attachment]");
            if (!attachElements.isEmpty()) {
                Element element = attachElements.first();
                if (null != element) {
                    String attachName = element.text();
                    String attachUrl = element.absUrl("href");
                }
            }
        } else {
            Elements elements = document.select("table[id^=forum]:contains(推荐主题) span a");
            elements.forEach(e -> {
                String detailUrl = e.absUrl("href");
                Request request = new Request.Builder().url(detailUrl).method(Method.GET).build();
                requestList.add(request);
            });

        }
        return requestList;

    }


}
