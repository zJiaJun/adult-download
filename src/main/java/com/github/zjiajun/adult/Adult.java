package com.github.zjiajun.adult;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    private Request request;


    private static class Builder {
        private Request request = new Request();

        public Builder url(String url) {
            request.setUrl(url);
            return this;
        }

        public Builder method(String method) {
            request.setMethod(method);
            return this;
        }

        public Builder loginName(String loginName) {
            request.setLoginName(loginName);
            return this;
        }

        public Builder loginPassword(String loginPassword) {
            request.setLoginPassword(loginPassword);
            return this;
        }

    }


}
