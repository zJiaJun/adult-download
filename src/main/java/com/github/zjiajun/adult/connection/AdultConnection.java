package com.github.zjiajun.adult.connection;

import com.github.zjiajun.adult.exception.AdultException;
import com.github.zjiajun.adult.tool.LoggerTool;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujiajun
 * 16/6/26 12:25
 *
 * 请求工具类
 */
public final class AdultConnection {

    private AdultConnection() {}

    public static Document connect(ConnectionRequest request) {
        return connect(request,null);
    }

    public static Document connect(ConnectionRequest request, ConnectionListener listener) {
        Objects.requireNonNull(request);
        Connection.Response response = null;
        try {
            Connection connect = Jsoup.connect(request.getUrl());
            if (request.getTimeout() > 0)
                connect.timeout(request.getTimeout());

            if (request.getMethod() != null)
                connect.method(Connection.Method.valueOf(request.getMethod()));

            if (request.getUserAgent() != null)
                connect.userAgent(request.getUserAgent());

            if (request.getPostDataCharset() != null)
                connect.postDataCharset(request.getPostDataCharset());

            if (request.getData() != null)
                connect.data(request.getData());

            /*
                jsoup竟然没有headers批量设置header方法
                已经向jsoup提交PR,应该会被merge

                已被merged https://github.com/jhy/jsoup/pull/725

             */
            if (request.getHeaders() != null)
                request.getHeaders().forEach(connect::header);

            if (request.getCookies() != null)
                connect.cookies(request.getCookies());

            response = connect.execute();
            if (listener != null) listener.success(response);
            return response.parse();
        } catch (IOException e) {
            if(listener != null) listener.failure(request,response,e);
            throw new AdultException(LoggerTool.getTrace(e));
        } finally {
            //一次请求结束后,不管成功还是失败,休眠几秒,以解决多线程大量访问网站,导致ip被封问题
            if (request.isSleep()) {
                try {
                    TimeUnit.SECONDS.sleep(request.getSleepSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
