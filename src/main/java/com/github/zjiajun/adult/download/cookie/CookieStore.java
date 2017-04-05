package com.github.zjiajun.adult.download.cookie;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public interface CookieStore {

    void store(HttpUrl httpUrl, List<Cookie> cookieList);

    List<Cookie> lookup(HttpUrl httpUrl);
}
