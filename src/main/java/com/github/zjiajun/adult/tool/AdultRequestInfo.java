package com.github.zjiajun.adult.tool;

import java.util.Map;
import java.util.Objects;

/**
 * Created by zhujiajun
 * 16/6/28 20:28
 *
 * 请求封装类
 */
public final class AdultRequestInfo {

    private final String url;
    private final int timeout;
    private final String method;
    private final String userAgent;
    private final String postDataCharset;
    private final Map<String,String> data;
    private final Map<String,String> headers;
    private final Map<String,String> cookies;
    private final boolean sleep;                //请求结束后是否休眠
    private final int sleepSeconds;             //休眠几秒

    private AdultRequestInfo(String url, int timeout, String method, String userAgent, String postDataCharset, Map<String, String> data, Map<String, String> headers, Map<String, String> cookies,boolean sleep,int sleepSeconds) {
        Objects.requireNonNull(url,"RequestInfo url must not be empty");
        this.url = url;
        this.timeout = timeout;
        this.method = method;
        this.userAgent = userAgent;
        this.postDataCharset = postDataCharset;
        this.data = data;
        this.headers = headers;
        this.cookies = cookies;
        this.sleep = sleep;
        this.sleepSeconds = sleepSeconds;
    }

    public static class Builder {

        private String url;
        private int timeout = 5000;
        private String method = "GET";
        private String userAgent;
        private String postDataCharset = "UTF-8";
        private Map<String,String> data;
        private Map<String,String> headers;
        private Map<String,String> cookies;
        private boolean sleep = false;
        private int sleepSeconds;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder postDataCharset(String postDataCharset) {
            this.postDataCharset = postDataCharset;
            return this;
        }

        public Builder data(Map<String,String> data) {
            this.data = data;
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder cookies(Map<String,String> cookies) {
            this.cookies = cookies;
            return this;
        }

        public Builder sleep(boolean sleep) {
            this.sleep = sleep;
            return this;
        }

        public Builder sleepSeconds(int sleepSeconds) {
            this.sleepSeconds = sleepSeconds;
            return this;
        }

        public AdultRequestInfo build() {
            return new AdultRequestInfo(url,timeout,method,userAgent,postDataCharset,data,headers,cookies,sleep,sleepSeconds);
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getMethod() {
        return method;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getPostDataCharset() {
        return postDataCharset;
    }

    public Map<String, String> getData() {
        return data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public boolean isSleep() {
        return sleep;
    }

    public int getSleepSeconds() {
        return sleepSeconds;
    }
}
