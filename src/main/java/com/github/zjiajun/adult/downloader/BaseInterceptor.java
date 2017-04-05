package com.github.zjiajun.adult.downloader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        setRequestHeader(builder);
        Request request = builder.build();
        return chain.proceed(request);
    }

    protected abstract void setRequestHeader(Request.Builder builder);

}
