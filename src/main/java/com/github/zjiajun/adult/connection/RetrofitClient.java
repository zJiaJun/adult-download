package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.login.LoginService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
            .connectTimeout(180, TimeUnit.SECONDS)
            .cookieJar(new DefaultCookieJar())
            .build();


    public Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = RetrofitClient.getInstance().getRetrofit("http://67.220.90.4/forum/");
        Map<String,String> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, sdch");
        headers.put("Connection", "keep-alive");
        headers.put("Host", "67.220.90.4");
        headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");

        Map<String,String> loginReqData = new HashMap<>();
        loginReqData.put("referer","index.php");
        loginReqData.put("loginfield","username");
        loginReqData.put("username","leoneye");
        loginReqData.put("password","wqcez136");
        loginReqData.put("questionid","0");
        loginReqData.put("answer","");
        loginReqData.put("cookietime","2592000");
        loginReqData.put("loginmode","");
        loginReqData.put("styleid","");
        loginReqData.put("loginsubmit","true");

        LoginService loginService = retrofit.create(LoginService.class);
        Call<ResponseBody> login = loginService.login(loginReqData, headers);
        Response<ResponseBody> execute = login.execute();
        System.out.println(execute.body());
        System.out.println(execute.message());
        System.out.println(execute.isSuccessful());


    }

}
