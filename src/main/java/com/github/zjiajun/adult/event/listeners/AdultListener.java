package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.*;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.Response;
import com.github.zjiajun.adult.http.RetrofitClient;
import com.github.zjiajun.adult.model.SiteData;
import com.github.zjiajun.adult.tool.FileUtils;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/10/21 10:29
 */
@Slf4j
public class AdultListener {


    @Subscribe
    public void onAdultRun(AdultEvent adultEvent) {
        SiteData siteData = adultEvent.getSiteData();
        Objects.requireNonNull(siteData, "Adult Site data should not be null");
        while (true) {
            try {
                Request request = siteData.takeRequest();
                EventBus.post(new RequestEvent(request, siteData));

                if (siteData.getPauseSeconds() > 0) {
                    TimeUnit.SECONDS.sleep(siteData.getPauseSeconds());
                }
            } catch (Exception e) {
                log.error("Adult run exception", e);
            }
        }
    }

    @Subscribe
    public void handleRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        //TODO 为了传递siteData, request event 添加了siteData字段, 觉得不太合适, 怎样优化. 不能使用threadLocal,event bus最终是线程池异步方式运行的
        SiteData siteData = requestEvent.getSiteData();
        Response response = RetrofitClient.INSTANCE.execute(request);
        EventBus.post(new ResponseEvent(response, siteData));
    }

    @Subscribe
    public void handleResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        //TODO 为了传递siteData, response event 添加了siteData字段, 同上
        SiteData siteData = responseEvent.getSiteData();
        if (response.isTextHtml()) {
            String content = response.getContent();
            Document document = Jsoup.parse(content, SexInSexConstant.BASE_URL);
            siteData.getPageParser().parse(document, siteData);
        } else {
            FileUtils.write(response.getBytes(), AppConfig.downloadPath() + response.getFileName());
        }
    }



}
