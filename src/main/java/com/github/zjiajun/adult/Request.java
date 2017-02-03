package com.github.zjiajun.adult;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public class Request {

    public enum Method {
        GET,
        POST;

        Method() {}
    }

    private Request() {}

    private String url;
    private Method method;
    private Map<String,String> data;

    public static class Builder {

        private Request request = new Request();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder get() {
            request.method = Method.GET;
            return this;
        }

        public Builder post() {
            request.method = Method.POST;
            return this;
        }

        public Builder data(Map<String,String> data) {
            request.data = data;
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

    public Method getMethod() {
        return method;
    }

    public Map<String, String> getData() {
        return data;
    }
}
