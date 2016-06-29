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

    public static String torrentDownloadPath() {
        return getStringConf("torrent.download.path",System.getProperty("user.home"));
    }

    public static List<String> uaList() {
        return config.getStringList("ua");
    }

    public static String sexInSexHost() {
        return getStringConf("torrent.sexInSex.host");
    }

    public static String sexInSexUrlPrefix() {
        return getStringConf("torrent.sexInSex.urlPrefix");
    }

    public static String sexInSexLoginUrl() {
        return sexInSexUrlPrefix() + getStringConf("torrent.sexInSex.loginUrl");
    }

    public static String sexInSexAsiaWuUrl() {
        return sexInSexUrlPrefix() + getStringConf("torrent.sexInSex.asiaWuUrl");
    }

    public static String sexInSexAsiaYouUrl() {
        return sexInSexUrlPrefix() + getStringConf("torrent.sexInSex.asiaYouUrl");
    }

    public static String sexInSexUserName() {
        return getStringConf("torrent.sexInSex.username");
    }

    public static String sexInSexPassword() {
        return getStringConf("torrent.sexInSex.password");
    }

    private static String getStringConf(String key) {
        return getStringConf(key,"");
    }

    private static String getStringConf(String key, String defaultValue) {
        Objects.requireNonNull(key,"Config key must not be null");
        return config.hasPath(key) ? config.getString(key) : defaultValue;
    }


}
