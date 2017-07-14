package com.github.zjiajun.adult.http;

import com.github.zjiajun.adult.config.Config;
import com.github.zjiajun.adult.http.cookie.DefaultCookieJar;
import com.github.zjiajun.adult.request.Request;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final Api api;

    private static final Logger LOGGER = LoggerFactory.getLogger("http.logger");

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new DefaultCookieJar())
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(30, 10,TimeUnit.SECONDS))
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LOGGER.info(message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Config.getInstance().baseUrl())
                .build();

        api = retrofit.create(Api.class);
    }

    private static class SingletonHolder {
        private static RetrofitClient instance = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.instance;
    }


    public com.github.zjiajun.adult.response.Response execute(Request request) throws IOException {
        Response<ResponseBody> retrofitResponse;
        switch (request.getMethod()) {
            case GET:
                retrofitResponse = get(request.getUrl(), request.getData());
                break;
            case POST:
                retrofitResponse = post(request.getUrl(),request.getData());
                break;
            default:
                throw new RuntimeException();
        }
        int code = retrofitResponse.code();
        ResponseBody responseBody = retrofitResponse.body();
        byte[] bytes = responseBody.bytes();
        String originalHtml = new String(bytes, Charset.forName(request.getCharset()));
        return new com.github.zjiajun.adult.response.Response(originalHtml, bytes, code);
    }


    private Response<ResponseBody> post(String url, Map<String,String> fieldMap) throws IOException {
        Call<ResponseBody> responseBodyCall = api.post(url, fieldMap);
        return responseBodyCall.execute();
    }

    private Response<ResponseBody> get(String url, Map<String,String> queryMap) throws IOException {
        Call<ResponseBody> responseBodyCall = queryMap == null ? api.get(url) : api.get(url, queryMap);
        return responseBodyCall.execute();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Api getApi() {
        return api;
    }

    public interface Api {

        @GET
        Call<ResponseBody> get(@Url String url);

        @GET
        Call<ResponseBody> get(@Url String url, @QueryMap Map<String, String> queryMap);

        @FormUrlEncoded
        @POST
        Call<ResponseBody> post(@Url String url, @FieldMap Map<String, String> fieldMap);

    }

}
