package com.github.zjiajun.adult;

import com.github.zjiajun.adult.connection.RetrofitClient;
import org.junit.Test;
import retrofit2.Retrofit;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class RetrofitClientTest {

    @Test
    public void buildRetrofitClientTest() {
        Retrofit retrofit = RetrofitClient.getInstance().getRetrofit("http://www.github.com");
    }
}
