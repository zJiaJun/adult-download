package com.github.zjiajun.adult.connection;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class MemoryCookieStore implements CookieStore{

    private final Map<HttpUrl,List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void store(HttpUrl httpUrl, List<Cookie> list) {
        cookieStore.put(httpUrl,list);
    }

    @Override
    public List<Cookie> lookup(HttpUrl httpUrl) {
        return cookieStore.get(httpUrl);
    }

}
