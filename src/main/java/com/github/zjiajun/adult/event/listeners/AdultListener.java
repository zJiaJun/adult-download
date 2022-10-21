package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.RequestType;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.*;
import com.github.zjiajun.adult.model.ParserResult;
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
public abstract class AdultListener {

    @Subscribe
    public void onAdultRun(AdultEvent adultEvent) {
        Request request = buildRequest();
        EventBus.post(new RequestEvent(request));
    }

    @Subscribe
    public void handleRequest(RequestEvent requestEvent) throws IOException {
        Request request = requestEvent.getRequest();
        Response response = RetrofitClient.INSTANCE.execute(request);
        switch (request.getRequestType()) {
            case LOGIN:
                //登录后, 处理论坛模块列表页
//                Request forumListReq = Request.builder().requestType(RequestType.WEB).url(SexInSexConstant.YOU_MA_FORUM_URL).charset("GBK").build();
                Request afterLoginReq = afterLoginRequest();
                EventBus.post(new RequestEvent(afterLoginReq));
                break;
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
    public void handleResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        String content = response.getContent();
        Document document = Jsoup.parse(content, SexInSexConstant.BASE_URL);
        EventBus.post(new ParserEvent(document));
    }

    @Subscribe
    public void parserContent(ParserEvent parserEvent) {
        Document document = parserEvent.getDocument();
        ParserResult parserResult = parserContent(document);
    }

    @Subscribe
    public void persistentContent(PersistentEvent persistentEvent) {
        String fileName = persistentEvent.getFileName();
        String downloadUrl = persistentEvent.getDownloadUrl();
        Request downloadRequest = Request.builder().requestType(RequestType.DOWNLOAD).extraData(RequestType.DOWNLOAD.data(fileName)).url(downloadUrl).build();
        EventBus.post(new RequestEvent(downloadRequest));
    }

    public abstract Request buildRequest();

    public abstract Request afterLoginRequest();

    public abstract ParserResult parserContent(Document document);

}
