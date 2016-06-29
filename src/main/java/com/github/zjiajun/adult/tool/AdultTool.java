package com.github.zjiajun.adult.tool;

import com.github.zjiajun.adult.exception.AdultException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.github.zjiajun.adult.tool.AdultConfig.*;

/**
 * Created by zhujiajun
 * 16/6/26 12:25
 *
 * 工具类
 */
public final class AdultTool {


    public static String randomUa() {
        List<String> uaList = uaList();
        Collections.shuffle(uaList);
        return uaList().get(new Random().nextInt(uaList.size()));
    }


    public static Document connect(AdultRequestInfo requestInfo) {
        return connect(requestInfo,null);
    }

    public static Document connect(AdultRequestInfo requestInfo,ConnectionListener listener) {
        Connection.Response response = null;
        try {
            Connection connect = Jsoup.connect(requestInfo.getUrl());
            if (requestInfo.getTimeout() > 0)
                connect.timeout(requestInfo.getTimeout());

            if (requestInfo.getMethod() != null)
                connect.method(Connection.Method.valueOf(requestInfo.getMethod()));

            if (requestInfo.getUserAgent() != null)
                connect.userAgent(requestInfo.getUserAgent());

            if (requestInfo.getPostDataCharset() != null)
                connect.postDataCharset(requestInfo.getPostDataCharset());

            if (requestInfo.getData() != null)
                connect.data(requestInfo.getData());

            if (requestInfo.getHeaders() != null)
                requestInfo.getHeaders().forEach(connect::header); //jsoup竟然没有headers批量设置header方法

            if (requestInfo.getCookies() != null)
                connect.cookies(requestInfo.getCookies());

            response = connect.execute();
            if (listener != null) listener.success(response);
            return response.parse();
        } catch (IOException e) {
            if(listener != null) listener.failure(requestInfo,response,e);
            throw new AdultException(LoggerTool.getTrace(e));
        } finally {
            if (requestInfo.isSleep()) {
                try {
                    TimeUnit.SECONDS.sleep(requestInfo.getSleepSeconds());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}
