package com.github.zjiajun.adult.request;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public class Request {

    private Request() {}

    private String url;
    private RequestMethod requestMethod;
    private boolean isLogin;
    private Map<String,String> data;

    public static class Builder {

        private Request request = new Request();

        public Builder url(String url) {
            request.url = url;
            return this;
        }


        public Builder login() {
            request.isLogin = true;
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

    public RequestMethod getMethod() {
        return requestMethod;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public Map<String, String> getData() {
        return data;
    }
}
