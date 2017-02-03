package com.github.zjiajun.adult.login;

import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.RetrofitClient;


/**
 * @author zhujiajun
 * @since 2017/2/2
 */
public abstract class AbstractLogin implements Login {

    @Override
    public void login(Request request) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        retrofitClient.post(request.getUrl(), request.getData());
    }

    protected abstract Request buildLoginRequestData();
}
