package com.github.zjiajun.adult;

import java.util.Objects;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public final class Request {

    private Request() {}

    private String url;
    private String method;
    private String loginName;
    private String loginPassword;

    public static class Builder {

        private Request request = new Request();

        public Builder url(String url) {
            request.url = url;
            return this;
        }

        public Builder method(String method) {
            request.method = method;
            return this;
        }

        public Builder loginName(String loginName) {
            request.loginName = loginName;
            return this;
        }

        public Builder loginPassword(String loginPassword) {
            request.loginPassword = loginPassword;
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

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }
}
