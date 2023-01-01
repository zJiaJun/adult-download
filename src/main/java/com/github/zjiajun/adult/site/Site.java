package com.github.zjiajun.adult.site;

import com.github.zjiajun.adult.context.AdultContext;
import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.AdultEvent;
import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.SiteData;
import com.github.zjiajun.adult.parser.PageParser;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2023/1/1 18:48
 */
public final class Site {

    private Site() {

    }

    private final SiteData data = new SiteData();

    public static class Builder {

        private final Site site = new Site();

        public Builder setRequest(Request request) {
            site.data.putRequest(request);
            return this;
        }

        public Builder setPageParser(PageParser pageParser) {
            site.data.setPageParser(pageParser);
            return this;
        }

        public Builder setPauseSeconds(int seconds) {
            site.data.setPauseSeconds(seconds);
            return this;
        }

        public Site build() {
            return site;
        }
    }

    public void start() {
        AdultContext.setSiteData(data);
        EventBus.post(new AdultEvent(data));
    }
}
