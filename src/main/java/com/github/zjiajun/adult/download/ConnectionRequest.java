package com.github.zjiajun.adult.download;

import com.github.zjiajun.adult.BasicRequest;

import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/28 20:28
 *
 * 请求相关信息
 */
public final class ConnectionRequest extends BasicRequest {

    private String postDataCharset = "UTF-8";
    private Map<String,String> data;

    private ConnectionRequest() {}

    public static class Builder {
        private ConnectionRequest connectionRequest = new ConnectionRequest();

        public Builder url(String url) {
            connectionRequest.setUrl(url);
            return this;
        }

        public Builder timeout(int timeout) {
            connectionRequest.setTimeout(timeout);
            return this;
        }

        public Builder method(String method) {
            connectionRequest.setMethod(method);
            return this;
        }

        public Builder userAgent(String userAgent) {
            connectionRequest.setUserAgent(userAgent);
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            connectionRequest.setHeaders(headers);
            return this;
        }

        public Builder cookies(Map<String,String> cookies) {
            connectionRequest.setCookies(cookies);
            return this;
        }

        public Builder sleep(boolean sleep) {
            connectionRequest.setSleep(sleep);
            return this;
        }

        public Builder sleepSeconds(int sleepSeconds) {
            connectionRequest.setSleepSeconds(sleepSeconds);
            return this;
        }

        public Builder postDataCharset(String postDataCharset) {
            connectionRequest.postDataCharset = postDataCharset;
            return this;
        }

        public Builder data(Map<String,String> data) {
            connectionRequest.data = data;
            return this;
        }

        public ConnectionRequest build() {
            return connectionRequest;
        }
    }


    public String getPostDataCharset() {
        return postDataCharset;
    }

    public Map<String, String> getData() {
        return data;
    }

}
