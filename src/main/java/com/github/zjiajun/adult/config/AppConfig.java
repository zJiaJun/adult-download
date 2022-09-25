package com.github.zjiajun.adult.config;

import com.typesafe.config.ConfigFactory;

import java.util.Objects;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/14 23:03
 */
public final class AppConfig {

    private AppConfig() {

    }

    private static final com.typesafe.config.Config CONFIG = ConfigFactory.load();


    public static long connectTimeout() {
        return CONFIG.hasPath("site.connect.timeout") ? CONFIG.getLong("site.connect.timeout") : 60L;
    }

    public static long writeTimeout() {
        return CONFIG.hasPath("site.write.timeout") ? CONFIG.getLong("site.write.timeout") : 60L;
    }

    public static long readTimeout() {
        return CONFIG.hasPath("site.read.timeout") ? CONFIG.getLong("site.read.timeout") : 60L;
    }

    public static String downloadPath() {
        return getString("site.download.path");
    }

    public static String cookieFilePath() {
        return getString("site.cookie.file.path");
    }

    public static boolean hasProxy() {
        return CONFIG.hasPath("site.proxy");
    }

    public static String proxyHost() {
        return getString("site.proxy.host");
    }

    public static int proxyPort() {
        return CONFIG.getInt("site.proxy.port");
    }


    public static String userName() {
        return getString("site.sexInSex.username");
    }

    public static String password() {
        return getString("site.sexInSex.password");
    }

    private static String getString(String key) {
        return getString(key, "");
    }

    private static String getString(String key, String defaultValue) {
        Objects.requireNonNull(key, "Config key must not be null");
        return CONFIG.hasPath(key) ? CONFIG.getString(key) : defaultValue;
    }

}
