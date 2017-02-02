package com.github.zjiajun.adult;

import com.github.zjiajun.adult.login.Login;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    private Login login;
    private Request request;

    public static class Builder {

        private Adult adult = new Adult();

        public Builder login(Login login) {
            adult.login = login;
            return this;
        }

        public Builder request(Request request) {
            adult.request = request;
            return this;
        }

        public Adult build() {
            return adult;
        }
    }


    public static void main(String[] args) {


    }


}
