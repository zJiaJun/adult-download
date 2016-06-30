package com.github.zjiajun.adult;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.zjiajun.adult.tool.AdultConfig.randomUA;
import static com.github.zjiajun.adult.tool.AdultConfig.sexInSexHost;

/**
 * Created by zhujiajun
 * 16/6/30 11:04
 */
public final class SiteContext {

    private SiteContext() {}

    private static final Map<String, String> HEADERS = new LinkedHashMap<>();    //请求头
    private static final Map<String, String> COOKIES = new LinkedHashMap<>();    //登陆后保存的cookies
    private static final String UA  = randomUA();                          //访问需要登陆权限的页面,必须和登陆的ua保持一致,似乎服务端有验证

    public static void initSexInSexHeaders() {
        HEADERS.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        HEADERS.put("Accept-Encoding", "gzip, deflate, sdch");
        HEADERS.put("Connection", "keep-alive");
        HEADERS.put("Host", sexInSexHost());
    }

    public static void setCookies(Map<String,String> cookies) {
        COOKIES.putAll(cookies);
    }

    public static Map<String,String> getCookies() {
        return COOKIES;
    }

    public static Map<String,String> getHeaders() {
        return HEADERS;
    }

    public static String getUA() {
        return UA;
    }

}
