package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.connection.cookie.DefaultCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public final class RetrofitClient {

    public static void main(String[] args) {
        RetrofitClient instance = RetrofitClient.getInstance();
        instance.get("http://67.220.90.4/forum/index.php",null);
    }

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Api api;

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(180, TimeUnit.SECONDS)
                .cookieJar(new DefaultCookieJar())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://example.org/")
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
            responseBodyCall.execute();
        } catch (IOException e) {
            e.printStackTrace();//FIXME
        }
    }

    public String get(String url, Map<String,String> queryMap) {
        Call<ResponseBody> responseBodyCall;
        if (queryMap == null)
            responseBodyCall = api.get(url);
        else
            responseBodyCall = api.get(url, queryMap);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            ResponseBody body = response.body();
            return new String(body.bytes(), Charset.forName("GBK"));
        } catch (IOException e) {
            e.printStackTrace();//FIXME
            return null;
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
        Call<ResponseBody> get(@Url String url);

        @GET
        Call<ResponseBody> get(@Url String url, @QueryMap Map<String,String> queryMap);

        @FormUrlEncoded
        @POST
        Call<ResponseBody> post(@Url String url, @FieldMap Map<String,String> fieldMap);

    }

}
