package com.github.zjiajun.adult.http.cookie;

import com.github.zjiajun.adult.config.Config;
import com.github.zjiajun.adult.tool.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.LineProcessor;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/2/3
 *
 */
public class DefaultCookieStore implements CookieStore {

    private final Map<String,List<Cookie>> cookieCache = Maps.newConcurrentMap();
    private final static String COOKIE_FILE = Config.getInstance().cookieFile();

    public DefaultCookieStore() {
        FileUtils.touch(COOKIE_FILE);
    }

    @Override
    public void store(HttpUrl httpUrl, List<Cookie> list) {
        if (Config.getInstance().loginUrl().equals(httpUrl.toString())) {
            cookieCache.put(httpUrl.host(), list);
            list.forEach(cookie -> FileUtils.append(cookie.toString() + '\n', COOKIE_FILE));
        }
    }

    @Override
    public List<Cookie> lookup(HttpUrl httpUrl) {
        String host = httpUrl.host();
        List<Cookie> cookieList = Collections.emptyList();
        if (Config.getInstance().host().equals(host)) {
            cookieList = cookieCache.get(host);
            if (cookieList == null) {
                cookieList = FileUtils.readLine(COOKIE_FILE, new LineProcessor<List<Cookie>>() {
                    final List<Cookie> cookieList = Lists.newArrayList();

                    @Override
                    public boolean processLine(String line) throws IOException {
                        Cookie cookie = Cookie.parse(httpUrl, line);
                        if (null != cookie) {
                            cookieList.add(cookie);
                        }
                        return true;
                    }

                    @Override
                    public List<Cookie> getResult() {
                        return cookieList;
                    }
                });

                if (null != cookieList && !cookieList.isEmpty()) {
                    cookieCache.put(host, cookieList);
                }
            }
        }
        return cookieList;
    }

}
