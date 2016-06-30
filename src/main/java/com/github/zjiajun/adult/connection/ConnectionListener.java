package com.github.zjiajun.adult.connection;

import org.jsoup.Connection;

/**
 * Created by zhujiajun
 * 16/6/28 21:13
 *
 * 连接回调
 */
public interface ConnectionListener {

    default void success(Connection.Response response) {
        //nothing
    }

    default void failure(ConnectionRequest request, Connection.Response response, Exception e) {
        System.err.println("Connect url wrong : " + request.getUrl());
        //可以把失败的url保存到集合里,后续继续处理
    }

}
