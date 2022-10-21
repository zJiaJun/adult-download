package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.RequestType;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.*;
import com.github.zjiajun.adult.http.RetrofitClient;
import com.github.zjiajun.adult.constatns.RequestMethod;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.Response;
import com.github.zjiajun.adult.tool.FileUtils;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/15 21:56
 */
@Slf4j
public class SexInSexListener {


    @Subscribe
    public void onSexInSexRun(SexInSexEvent sexInSexEvent) {
        Request loginRequest = Request.builder()
                .requestType(RequestType.LOGIN)
                .url(SexInSexConstant.LOGIN_URL).charset("GBK")
                .formData(RequestType.LOGIN.data(SexInSexConstant.HOST))
                .requestMethod(RequestMethod.POST).build();
        EventBus.post(new RequestEvent(loginRequest));
    }

    @Subscribe
    public void handleRequest(RequestEvent requestEvent) throws IOException {
        Request request = requestEvent.getRequest();
        Response response = RetrofitClient.INSTANCE.execute(request);
        switch (request.getRequestType()) {
            case LOGIN:
                //TODO 判断账号/密码登录成功
                //登录后, 处理论坛模块列表页
                Request forumListReq = Request.builder().requestType(RequestType.WEB).url(SexInSexConstant.YOU_MA_FORUM_URL).charset("GBK").build();
                EventBus.post(new RequestEvent(forumListReq));
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
        //获取帖子列表页
        Elements elements = document.select("table[id^=forum]:contains(版块主题) span a");
        if (elements.isEmpty()) {
            //是空的, 说明已经是具体的帖子详情页
            Elements imgElements = document.select("div.t_msgfont img[src^=http]");
            if (!imgElements.isEmpty() && null != imgElements.first()) {
                String imgUrl = imgElements.first().attr("src");
                String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                EventBus.post(new PersistentEvent(imgFileName, imgUrl));
            }

            Elements attachElements = document.select("dl.t_attachlist a[href^=attachment]");
            if (!attachElements.isEmpty()) {
                Element element = attachElements.first();
                if (null != element) {
                    String attachName = element.text();
                    String attachUrl = element.absUrl("href");
                    EventBus.post(new PersistentEvent(attachName, attachUrl));
                }
            }
        } else {
            //循环处理列表页中的每一个帖子
            elements.forEach(e -> {
                String detailUrl = e.absUrl("href");
                Request subRequest = Request.builder().requestType(RequestType.WEB).url(detailUrl).requestMethod(RequestMethod.GET).build();
                EventBus.post(new RequestEvent(subRequest));
            });
        }
    }

    @Subscribe
    public void persistentContent(PersistentEvent persistentEvent) {
        String fileName = persistentEvent.getFileName();
        String downloadUrl = persistentEvent.getDownloadUrl();
        Request downloadRequest = Request.builder().requestType(RequestType.DOWNLOAD).extraData(RequestType.DOWNLOAD.data(fileName)).url(downloadUrl).build();
        EventBus.post(new RequestEvent(downloadRequest));
    }

}
