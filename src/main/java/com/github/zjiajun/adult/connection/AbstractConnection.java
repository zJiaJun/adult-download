package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class AbstractConnection {

    private RetrofitClient retrofitClient = RetrofitClient.getInstance();

    protected abstract void beforeConnection(Request request);

    protected void connect(Request request) {
        beforeConnection(request);
        ResponseBody responseBody = null;
        try {
            switch (request.getMethod()) {
                case GET:
                    responseBody = retrofitClient.get(request.getUrl(), request.getData());
                    break;
                case POST:
                    responseBody = retrofitClient.post(request.getUrl(), request.getData());
                    break;
                default:
                    throw new RuntimeException();
            }
        } catch (IOException e) {
            e.printStackTrace();//FIXME
        }
        afterConnection(responseBody);
    }

    protected abstract void afterConnection(ResponseBody responseBody);
}
