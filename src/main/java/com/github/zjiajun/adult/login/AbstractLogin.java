package com.github.zjiajun.adult.login;

import com.github.zjiajun.adult.LoginRequest;


/**
 * @author zhujiajun
 * @since 2017/2/2
 */
public abstract class AbstractLogin implements Login {

    @Override
    public void login() {
        LoginRequest loginRequest = initLoginRequestData();

    }

    protected abstract LoginRequest initLoginRequestData();
}
