package com.github.zjiajun.adult;

import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/29 11:34
 *
 * 请求基类
 */
public class BasicRequest {

    private String url;
    private int timeout = 5000;
    private String method = "GET";
    private String userAgent;
    private Map<String,String> headers;
    private Map<String,String> cookies;
    private boolean sleep;                //请求结束后是否休眠
    private int sleepSeconds;             //休眠几秒

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public int getSleepSeconds() {
        return sleepSeconds;
    }

    public void setSleepSeconds(int sleepSeconds) {
        this.sleepSeconds = sleepSeconds;
    }
}
