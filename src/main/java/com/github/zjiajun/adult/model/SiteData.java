package com.github.zjiajun.adult.model;

import com.github.zjiajun.adult.exception.AdultException;
import com.github.zjiajun.adult.parser.PageParser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2023/1/1 19:11
 */
public class SiteData {

    private final BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();

    private PageParser pageParser;

    private int pauseSeconds;

    private String userAgent;

    public void putRequest(Request request) {
        try {
            requestQueue.put(request);
        } catch (InterruptedException e) {
            throw new AdultException(this.getClass().getSimpleName() + " putRequest InterruptedException", e);
        }
    }

    public Request takeRequest() {
        try {
            return requestQueue.take();
        } catch (InterruptedException e) {
            throw new AdultException(this.getClass().getSimpleName() + " takeRequest InterruptedException", e);
        }
    }

    public void setPageParser(PageParser pageParser) {
        this.pageParser = pageParser;
    }

    public PageParser getPageParser() {
        return pageParser;
    }

    public int getPauseSeconds() {
        return pauseSeconds;
    }

    public void setPauseSeconds(int pauseSeconds) {
        this.pauseSeconds = pauseSeconds;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
