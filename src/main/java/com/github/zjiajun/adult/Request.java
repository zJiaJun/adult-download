package com.github.zjiajun.adult;

import java.util.Objects;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public class Request {

    protected Request() {}

    private String url;
    private String method;

    public static class Builder {

        private Request request = new Request();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder get() {
            request.method = "GET";
            return this;
        }

        public Builder post() {
            request.method = "POST";
            return this;
        }

        public Request build() {
            Objects.requireNonNull(request.url, "Request url should not be empty");
            return request;
        }

    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

}
