package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.RequestType;
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
                EventBus.post(new RequestEvent(request));

                if (siteData.getPauseSeconds() > 0) {
                    TimeUnit.SECONDS.sleep(siteData.getPauseSeconds());
                }
            } catch (Exception e) {
                log.error("Adult run exception", e);
            }
        }
    }

    @Subscribe
    public void sendRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        Response response = RetrofitClient.INSTANCE.execute(request);
        switch (request.getRequestType()) {
            case WEB:
                EventBus.post(new ResponseEvent(response));
                break;
            case DOWNLOAD:
                FileUtils.write(response.getBytes(), AppConfig.downloadPath() + request.getExtraData().get(SexInSexConstant.FILE_NAME_KEY));
                break;
            default:
                throw new RuntimeException();
        }
    }

    @Subscribe
    public void processResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        String content = response.getContent();
        Document document = Jsoup.parse(content, SexInSexConstant.BASE_URL);
        EventBus.post(new ParserEvent(document));
    }


    @Subscribe
    public void parserContent(ParserEvent parserEvent) {
        Document document = parserEvent.getDocument();
//        handleContent(document);
    }

    @Subscribe
    public void persistentContent(PersistentEvent persistentEvent) {
        String fileName = persistentEvent.getFileName();
        String downloadUrl = persistentEvent.getDownloadUrl();
        Request downloadRequest = Request.builder().requestType(RequestType.DOWNLOAD).extraData(RequestType.DOWNLOAD.data(fileName)).url(downloadUrl).build();
        EventBus.post(new RequestEvent(downloadRequest));
    }


}
