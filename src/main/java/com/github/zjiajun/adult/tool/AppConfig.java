package com.github.zjiajun.adult.tool;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

/**
 * Created by zhujiajun
 * 16/6/25 21:57
 */
public class AppConfig {

    private static Config config = ConfigFactory.load();


    public static String getPicDownloadPath() {
        return getStringConf("pic.download.path",System.getProperty("user.home"));
    }

    public static List<String> getUaList() {
        return config.getStringList("ua");
    }

    public static String getStringConf(String key, String defaultValue) {
        if (key == null) throw new IllegalArgumentException("key should be not null");
        return config.getString(key) == null ? defaultValue : config.getString(key);
    }


}
