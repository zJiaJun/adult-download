package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.connection.cookie.DefaultCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

    public static void main(String[] args) {
        RetrofitClient instance = RetrofitClient.getInstance();
        instance.get("http://httpbin.org/",null);
    }

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Api api;

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
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
            Response<ResponseBody> response = responseBodyCall.execute();
            System.out.println(response.body());
            System.out.println(response.headers().toString());
            System.out.println(response.body().string());
            Document gbk = Jsoup.parse(response.body().byteStream(), "gbk", "http://67.220.90.4/forum/");
            System.out.println(gbk.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String url, Map<String,String> queryMap) {
        Call<ResponseBody> responseBodyCall;
        if (queryMap == null)
            responseBodyCall = api.get(url);
        else
            responseBodyCall = api.get(url, queryMap);
        try {
            Response<ResponseBody> response = responseBodyCall.execute();
            String html = response.body().string();
            System.out.println(html);
            Document parse = Jsoup.parse(html);
            System.out.println(parse.html());
            Elements elements = parse.select("table[id^=forum]:contains(推荐主题) span a");
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
        Call<ResponseBody> get(@Url String url);

        @GET
        @Headers("Content-Type:text/html;charset=utf-8")
        Call<ResponseBody> get(@Url String url, @QueryMap Map<String,String> queryMap);

        @POST
        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded;charset=gbk")
        Call<ResponseBody> post(@Url String url, @FieldMap Map<String,String> fieldMap);

    }

}
