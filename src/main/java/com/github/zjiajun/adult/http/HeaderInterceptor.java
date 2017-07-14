package com.github.zjiajun.adult.http;

import okhttp3.Request;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class HeaderInterceptor extends BaseInterceptor {

    @Override
    protected void setRequestHeader(Request.Builder builder) {
        builder.header("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36");
    }
}
