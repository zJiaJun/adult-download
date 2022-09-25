package com.github.zjiajun.adult.http.cookie;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.tool.FileUtils;
import com.google.common.collect.Maps;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class DefaultCookieJar implements CookieJar {

    private final Map<String, List<Cookie>> cookieCache = Maps.newConcurrentMap();

    private final static String COOKIE_FILE = AppConfig.cookieFilePath() + "cookie";


    public DefaultCookieJar() {
        FileUtils.touch(COOKIE_FILE);

    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (SexInSexConstant.LOGIN_URL.equals(httpUrl.toString())) {
            cookieCache.put(httpUrl.host(), list);
            list.forEach(cookie -> FileUtils.append(cookie.toString() + '\n', COOKIE_FILE));
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        String host = httpUrl.host();
        List<Cookie> cookieList = Collections.emptyList();
        if (SexInSexConstant.HOST.equals(host)) {
            cookieList = cookieCache.get(host);
            if (cookieList == null) {
                List<String> cookieLine = FileUtils.readLine(COOKIE_FILE);
                cookieList = cookieLine.stream().filter(Objects::nonNull)
                        .map(cLine -> Cookie.parse(httpUrl, cLine))
                        .collect(Collectors.toList());

                if (!cookieList.isEmpty()) {
                    cookieCache.put(host, cookieList);
                }
            }
        }
        return cookieList;
    }

}
