package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.Request;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class AbstractConnection {

    private RetrofitClient retrofitClient = RetrofitClient.getInstance();

    protected abstract void beforeConnect(Request request);

    protected void connect(Request request) {
        beforeConnect(request);
        switch (request.getMethod()) {
            case GET:
                String pageHtml = retrofitClient.get(request.getUrl(), request.getData());
                break;
            case POST:
                retrofitClient.post(request.getUrl(), request.getData());
                break;
            default:
                throw new RuntimeException();
        }
        afterConnect();
    }

    protected abstract void afterConnect();
}
