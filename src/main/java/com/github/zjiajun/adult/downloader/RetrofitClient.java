package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.downloader.cookie.DefaultCookieJar;
import com.github.zjiajun.adult.request.Request;
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


    public com.github.zjiajun.adult.response.Response execute(Request request) throws IOException {
        com.github.zjiajun.adult.response.Response response;
        switch (request.getMethod()) {
            case GET:
                response = get(request.getUrl(), request.getData());
                break;
            case POST:
                response = post(request.getUrl(),request.getData());
                break;
            default:
                throw new RuntimeException();
        }
        return response;
    }


    private com.github.zjiajun.adult.response.Response post(String url, Map<String,String> fieldMap) throws IOException {
        Call<ResponseBody> responseBodyCall = api.post(url, fieldMap);
        Response<ResponseBody> response = responseBodyCall.execute();
        ResponseBody body = response.body();
        return null;
    }

    private com.github.zjiajun.adult.response.Response get(String url, Map<String,String> queryMap) throws IOException {
        Call<ResponseBody> responseBodyCall = queryMap == null ? api.get(url) : api.get(url, queryMap);
        Response<ResponseBody> response = responseBodyCall.execute();
        ResponseBody body = response.body();
        return null;
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
