package com.github.zjiajun.adult.connection;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class RetrofitClient {

    private RetrofitClient() {}

    private static class SingletonHolder {
        private static RetrofitClient retrofitClient = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.retrofitClient;
    }

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .connectTimeout(120, TimeUnit.SECONDS)
            .cookieJar(CookieJar.NO_COOKIES)//FIXME
            .build();


    public Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

}
