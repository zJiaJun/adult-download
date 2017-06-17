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
    private Method method = Method.GET;
    private String charset = "UTF-8";
    private boolean isLogin = false;
    private Map<String,String> data;

    public static class Builder {

        private Request request = new Request();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder method(Method method) {
            request.method = method;
            return this;
        }

        public Builder charset(String charset) {
            request.charset = charset;
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

    public Method getMethod() {
        return method;
    }

    public String getCharset() {
        return charset;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", charset='" + charset + '\'' +
                ", isLogin=" + isLogin +
                ", data=" + data +
                '}';
    }
}
