package com.github.zjiajun.adult.login;

import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.AbstractConnection;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class LoginService extends AbstractConnection implements Login {

    @Override
    protected void beforeConnection(Request request) {
    }

    @Override
    public void login(Request request) {
        super.connect(request);
    }

    @Override
    protected void afterConnection(Request request, ResponseBody responseBody) throws IOException {

    }
}
