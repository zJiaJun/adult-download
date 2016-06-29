package com.github.zjiajun.adult.tool;

import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/28 20:28
 *
 * 请求相关信息
 */
public final class AdultRequestInfo extends AdultBasicInfo {

    private String postDataCharset = "UTF-8";
    private Map<String,String> data;

    private AdultRequestInfo() {}

    public static class Builder {
        private AdultRequestInfo adultRequestInfo = new AdultRequestInfo();

        public Builder url(String url) {
            adultRequestInfo.setUrl(url);
            return this;
        }

        public Builder timeout(int timeout) {
            adultRequestInfo.setTimeout(timeout);
            return this;
        }

        public Builder method(String method) {
            adultRequestInfo.setMethod(method);
            return this;
        }

        public Builder userAgent(String userAgent) {
            adultRequestInfo.setUserAgent(userAgent);
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            adultRequestInfo.setHeaders(headers);
            return this;
        }

        public Builder cookies(Map<String,String> cookies) {
            adultRequestInfo.setCookies(cookies);
            return this;
        }

        public Builder sleep(boolean sleep) {
            adultRequestInfo.setSleep(sleep);
            return this;
        }

        public Builder sleepSeconds(int sleepSeconds) {
            adultRequestInfo.setSleepSeconds(sleepSeconds);
            return this;
        }

        public Builder postDataCharset(String postDataCharset) {
            adultRequestInfo.postDataCharset = postDataCharset;
            return this;
        }

        public Builder data(Map<String,String> data) {
            adultRequestInfo.data = data;
            return this;
        }

        public AdultRequestInfo build() {
            return adultRequestInfo;
        }
    }


    public String getPostDataCharset() {
        return postDataCharset;
    }

    public Map<String, String> getData() {
        return data;
    }

}
