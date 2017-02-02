package com.github.zjiajun.adult;

import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/2/2
 */
public class LoginRequest {

    private LoginRequest() {}

    private String url;
    private String name;
    private String password;
    private Map<String,String> data;


    public static class Builder {

        LoginRequest loginRequest = new LoginRequest();

        public Builder url(String url) {
            loginRequest.url = url;
            return this;
        }

        public Builder name(String name) {
            loginRequest.name = name;
            return this;
        }

        public Builder password(String password) {
            loginRequest.password = password;
            return this;
        }

        public Builder data(Map<String,String> data) {
            loginRequest.data = data;
            return this;
        }

        public LoginRequest build() {
            return loginRequest;
        }

    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, String> getData() {
        return data;
    }
}
