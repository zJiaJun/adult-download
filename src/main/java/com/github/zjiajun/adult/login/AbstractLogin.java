package com.github.zjiajun.adult.login;

import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.AbstractConnection;


/**
 * @author zhujiajun
 * @since 2017/2/2
 */
public abstract class AbstractLogin extends AbstractConnection implements Login {

    @Override
    protected void beforeConnect(Request request) {

    }

    @Override
    public void login(Request request) {
        super.connect(request);
    }


    @Override
    protected void afterConnect() {

    }
}
