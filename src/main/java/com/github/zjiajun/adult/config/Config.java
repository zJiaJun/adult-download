package com.github.zjiajun.adult.config;

import com.typesafe.config.ConfigFactory;

import java.util.Objects;

/**
 * @author zhujiajun
 * @since 2017/6/30
 */
public final class Config {

    private Config() {}

    private static class SingletonHolder {
        private static Config instance = new Config();
    }

    public static Config getInstance() {
        return SingletonHolder.instance;
    }

    private static com.typesafe.config.Config config = ConfigFactory.load("adult");

    public String host() {
        return getString("site.sexInSex.host");
    }

    public String baseUrl() {
        return getString("site.sexInSex.baseUrl");
    }

    public String loginUrl() {
        return baseUrl() + getString("site.sexInSex.loginUrl");
    }

    public String asiaYouUrl() {
        return baseUrl() + getString("site.sexInSex.asiaYouUrl");
    }

    public String userName() {
        return getString("site.sexInSex.username");
    }

    public String password() {
        return getString("site.sexInSex.password");
    }

    public String downloadPath() {
        return getString("site.download.path");
    }

    public String cookieFile() {
        return getString("site.cookie.file");
    }

    private  String getString(String key) {
        return getString(key, "");
    }

    private  String getString(String key, String defaultValue) {
        Objects.requireNonNull(key,"Config key must not be null");
        return config.hasPath(key) ? config.getString(key) : defaultValue;
    }


}
