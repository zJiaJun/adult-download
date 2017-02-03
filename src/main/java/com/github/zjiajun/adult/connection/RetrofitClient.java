package com.github.zjiajun.adult.connection;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public final class RetrofitClient {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Api api;

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(180, TimeUnit.SECONDS)
                .cookieJar(new DefaultCookieJar())
                .build();

        //不设置baseUrl来处理多站点请求，先暂定这样
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .build();

        api = retrofit.create(Api.class);
    }

    private static class SingletonHolder {
        private static RetrofitClient retrofitClient = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.retrofitClient;
    }

    public void post(String url, Map<String,String> fieldMap) {
        Call<ResponseBody> responseBodyCall = api.post(url, fieldMap);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String url, Map<String,String> queryMap) {
        Call<ResponseBody> responseBodyCall = api.get(url, queryMap);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    private interface Api {

        @GET
        Call<ResponseBody> get(@Url String url, @QueryMap Map<String,String> queryMap);

        @POST
        @FormUrlEncoded
        Call<ResponseBody> post(@Url String url, @FieldMap Map<String,String> fieldMap);

    }

}
