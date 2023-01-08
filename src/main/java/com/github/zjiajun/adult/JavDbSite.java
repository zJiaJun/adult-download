package com.github.zjiajun.adult;

import com.github.zjiajun.adult.constatns.RequestMethod;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.SiteData;
import com.github.zjiajun.adult.parser.PageParser;
import com.github.zjiajun.adult.site.Site;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2023/1/2 22:11
 */
public class JavDbSite {

    public static void main(String[] args) {
        Request request = Request.builder()
                .url("https://javdb.com").charset("GBK")
                .requestMethod(RequestMethod.GET).build();
        Site site = new Site.Builder()
                .setRequest(request)
                .setPauseSeconds(3)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .setPageParser(new JavDbPageParser()).build();
        site.start();
    }

    private static class JavDbPageParser implements PageParser {

        @Override
        public void parse(Document document, SiteData siteData) {

        }
    }

}
