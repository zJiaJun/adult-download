package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.common.MessageQueue;
import com.github.zjiajun.adult.common.MessageRecord;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public abstract class AbstractConnection {

    private final RetrofitClient retrofitClient = RetrofitClient.getInstance();

    /**
     * 请求之前的回调操作
     * @param request 请求对象
     */
    protected abstract void beforeConnection(Request request);

    protected void connect(Request request) {
        beforeConnection(request);
        ResponseBody responseBody;
        Page page = new Page();
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
            String originalHtml = afterConnection(responseBody);
            page.setHtml(originalHtml);
            page.setRequest(request);
        } catch (IOException e) {
            exceptionCaught(request, e);
        }
    }


    /**
     * 发生异常时回调操作
     * @param request   请求对象
     * @param exception 异常信息
     */
    protected abstract void exceptionCaught(Request request, Exception exception);

    /**
     * 请求完成后的回调操作
     * @param responseBody  返回对象
     * @throws IOException
     */
    protected String afterConnection(ResponseBody responseBody) throws IOException {
        return responseBody.string();
    }
}
