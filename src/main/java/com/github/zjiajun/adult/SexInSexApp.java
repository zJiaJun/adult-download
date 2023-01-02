package com.github.zjiajun.adult;

import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.constatns.RequestMethod;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.SiteData;
import com.github.zjiajun.adult.parser.PageParser;
import com.github.zjiajun.adult.site.Site;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/15 21:56
 */
@Slf4j
public class SexInSexApp {


    public static void main(String[] args) {
        Request request = Request.builder()
                .url(SexInSexConstant.YOU_MA_FORUM_URL).charset("GBK")
                .requestMethod(RequestMethod.GET).build();
        Site site = new Site.Builder()
                .setRequest(request)
                .setPauseSeconds(3)
                .setPageParser(new SexInSexPageParser()).build();
        site.start();
    }


    private static class SexInSexPageParser implements PageParser {

        @Override
        public void parse(Document document, SiteData siteData) {
            //获取帖子列表页
            Elements elements = document.select("table[id^=forum]:contains(版块主题) span a");
            if (elements.isEmpty()) {
                //是空的, 说明已经是具体的帖子详情页
                Elements imgElements = document.select("div.t_msgfont img[src^=http]");
                if (!imgElements.isEmpty() && imgElements.first() != null) {
                    String imgUrl = imgElements.first().attr("src");
                    String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                    Request downloadRequest = Request.builder().url(imgUrl).fileName(imgFileName).build();
                    siteData.putRequest(downloadRequest);
                }

                Elements attachElements = document.select("dl.t_attachlist a[href^=attachment]");
                if (!attachElements.isEmpty()) {
                    Element element = attachElements.first();
                    if (null != element) {
                        String attachName = element.text();
                        String attachUrl = element.absUrl("href");
                        Request downloadRequest = Request.builder().url(attachUrl).fileName(attachName).build();
                        siteData.putRequest(downloadRequest);
                    }
                }
            } else {
                //循环处理列表页中的每一个帖子
                elements.forEach(e -> {
                    String detailUrl = e.absUrl("href");
                    Request subRequest = Request.builder().url(detailUrl).build();
                    siteData.putRequest(subRequest);
                });
            }
        }
    }

}
