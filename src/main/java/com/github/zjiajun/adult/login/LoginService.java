package com.github.zjiajun.adult.login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public interface LoginService {


    @FormUrlEncoded
    @POST("logging.php?action=login")
    Call<ResponseBody> login(@FieldMap Map<String,String> map, @HeaderMap Map<String,String> headers);
}
