package com.github.zjiajun.adult.downloader;

import org.jsoup.Connection;

/**
 * Created by zhujiajun
 * 16/6/28 21:13
 *
 * 连接回调
 */
@FunctionalInterface
public interface ConnectionListener {

    void handlerResponse(Connection.Response response);

    default void success(Connection.Response response) {
        handlerResponse(response);
    }

    default void failure(ConnectionRequest request, Connection.Response response, Exception e) {
        System.err.println("Connect url wrong : " + request.getUrl());
        handlerResponse(response);
        //可以把失败的url保存到集合里,后续继续处理
    }

}
