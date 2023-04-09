package com.github.zjiajun.adult.http;

import com.github.zjiajun.adult.config.AppConfig;
import com.github.zjiajun.adult.constatns.SexInSexConstant;
import com.github.zjiajun.adult.exception.AdultException;
import com.github.zjiajun.adult.http.cookie.DefaultCookieJar;
import com.github.zjiajun.adult.model.Request;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
@Slf4j
public final class RetrofitClient {

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final Api api;

    public static RetrofitClient INSTANCE;

    public static void init() {
        INSTANCE = new RetrofitClient();
    }

    private RetrofitClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
//                .cookieJar(new DefaultCookieJar())
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(AppConfig.connectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(AppConfig.writeTimeout(), TimeUnit.SECONDS).readTimeout(AppConfig.readTimeout(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(30, 10, TimeUnit.SECONDS))
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NonNull String message) {
                        log.info(message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC));
        if (AppConfig.hasProxy()) {
            log.info("find proxy config, proxy.host = {}, proxy.port = {}", AppConfig.proxyHost(), AppConfig.proxyPort());
            okHttpBuilder.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(AppConfig.proxyHost(), AppConfig.proxyPort())));
        }
        okHttpClient = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(SexInSexConstant.BASE_URL)
                .build();

        api = retrofit.create(Api.class);
    }


    public com.github.zjiajun.adult.model.Response execute(Request request) {
        Response<ResponseBody> retrofitResponse;
        try {
            switch (request.getRequestMethod()) {
                case GET:
                    retrofitResponse = get(request.getUrl(), request.getFormData(), request.getUserAgent());
                    break;
                case POST:
                    retrofitResponse = post(request.getUrl(), request.getFormData(), request.getUserAgent());
                    break;
                default:
                    throw new RuntimeException();
            }
            int code = retrofitResponse.code();
            try (ResponseBody errorBody = retrofitResponse.errorBody()) {
                if (errorBody != null) {
                    throw new AdultException(errorBody.string());
                }
            }
            try (ResponseBody responseBody = retrofitResponse.body()) {
                if (responseBody == null) {
                    return null;
                }
                byte[] bytes = responseBody.bytes();
                String contentType = Optional.ofNullable(responseBody.contentType()).map(MediaType::toString).orElse("");
                com.github.zjiajun.adult.model.Response.ResponseBuilder responseBuilder = com.github.zjiajun.adult.model.Response.builder();
                responseBuilder.contentType(contentType).statusCode(code);
                //TODO 需要优化
                if ("application/x-bittorrent".equals(contentType) || "image/jpeg".equals(contentType)) {
                    return responseBuilder.bytes(bytes).fileName(request.getFileName()).build();
                } else if (contentType.startsWith("text/html")) {
                    String originalHtml = new String(bytes, Charset.forName(request.getCharset()));
                    return responseBuilder.content(originalHtml).build();
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            log.error("request exception", e);
            return null;
        }
    }


    private Response<ResponseBody> post(String url, Map<String, String> fieldMap, String userAgent) throws IOException {
        Call<ResponseBody> responseBodyCall = api.post(url, fieldMap, userAgent);
        return responseBodyCall.execute();
    }

    private Response<ResponseBody> get(String url, Map<String, String> queryMap, String userAgent) throws IOException {
        Call<ResponseBody> responseBodyCall = queryMap == null ? api.get(url, userAgent) : api.get(url, queryMap, userAgent);
        return responseBodyCall.execute();
    }


    public Api getApi() {
        return api;
    }

    public interface Api {

        @GET
        Call<ResponseBody> get(@Url String url, @Header("User-Agent") String userAgent);

        @GET
        Call<ResponseBody> get(@Url String url, @QueryMap Map<String, String> queryMap, @Header("User-Agent") String userAgent);

        @FormUrlEncoded
        @POST
        Call<ResponseBody> post(@Url String url, @FieldMap Map<String, String> fieldMap, @Header("User-Agent") String userAgent);

    }

}
