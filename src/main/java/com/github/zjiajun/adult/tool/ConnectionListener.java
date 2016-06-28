package com.github.zjiajun.adult.tool;

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

    default void failure(AdultRequestInfo requestInfo,Connection.Response response) {
        //nothing
    }

}
