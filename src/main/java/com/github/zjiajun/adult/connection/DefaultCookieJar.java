package com.github.zjiajun.adult.connection;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class DefaultCookieJar implements CookieJar {

    private StorePolicy storePolicy;
    private CookieStore cookieStore;

    public DefaultCookieJar() {
        this.storePolicy = StorePolicy.MEMORY;
        this.cookieStore = new MemoryCookieStore();
    }

    public DefaultCookieJar(StorePolicy storePolicy) {
        this.storePolicy = storePolicy;
        switch (this.storePolicy) {
            case MEMORY:
                this.cookieStore = new MemoryCookieStore();
                break;
            case FILE:
//                this.cookieStore = new FileCookieStore();
                break;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cookieStore.store(httpUrl,list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return this.cookieStore.lookup(httpUrl);
    }

    /**
     * Cookie持久化保存策略
     * 1.内存
     * 2.文件
     */
    public enum StorePolicy {
        MEMORY,
        FILE;

        private StorePolicy() {}
    }
}
