package com.github.zjiajun.adult.http.cookie;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class DefaultCookieJar implements CookieJar {

    private final CookieStore cookieStore;

    public DefaultCookieJar() {
        this.cookieStore = new DefaultCookieStore();
    }

    public DefaultCookieJar(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        cookieStore.store(httpUrl, list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return cookieStore.lookup(httpUrl);
    }

}
