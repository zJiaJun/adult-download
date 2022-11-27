package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.RequestType;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.*;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.Response;
import com.github.zjiajun.adult.http.RetrofitClient;
import com.github.zjiajun.adult.tool.FileUtils;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/10/21 10:29
 */
@Slf4j
public abstract class AbstractAdultListener implements AdultListener {

    public abstract Request buildRequest();

    public abstract void handleContent(Document document);

    @Subscribe
    public void onAdultRun(AdultEvent adultEvent) {
        Request request = buildRequest();
        EventBus.post(new RequestEvent(request));
    }

    @Override
    @Subscribe
    public void onRequestSend(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        Response response;
        try {
            response = RetrofitClient.INSTANCE.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    @Subscribe
    public void onResponseReturn(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        String content = response.getContent();
        Document document = Jsoup.parse(content, SexInSexConstant.BASE_URL);
        EventBus.post(new ParserEvent(document));
    }


    @Subscribe
    public void parserContent(ParserEvent parserEvent) {
        Document document = parserEvent.getDocument();
        handleContent(document);
    }

    @Subscribe
    public void persistentContent(PersistentEvent persistentEvent) {
        String fileName = persistentEvent.getFileName();
        String downloadUrl = persistentEvent.getDownloadUrl();
        Request downloadRequest = Request.builder().requestType(RequestType.DOWNLOAD).extraData(RequestType.DOWNLOAD.data(fileName)).url(downloadUrl).build();
        EventBus.post(new RequestEvent(downloadRequest));
    }


}
