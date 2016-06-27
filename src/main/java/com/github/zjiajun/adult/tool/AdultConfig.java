package com.github.zjiajun.adult.tool;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.Objects;

/**
 * Created by zhujiajun
 * 16/6/25 21:57
 *
 * 配置文件
 */
public final class AdultConfig {

    private static Config config = ConfigFactory.load();


    public static String rosixzUrl() {
        return getStringConf("pic.rosixz.url");
    }

    public static String picDownloadPath() {
        return getStringConf("pic.download.path",System.getProperty("user.home"));
    }

    public static List<String> uaList() {
        return config.getStringList("ua");
    }

    private static String getStringConf(String key) {
        return getStringConf(key,"");
    }

    private static String getStringConf(String key, String defaultValue) {
        Objects.requireNonNull(key,"config key must not be null");
        return config.hasPath(key) ? config.getString(key) : defaultValue;
    }


}
