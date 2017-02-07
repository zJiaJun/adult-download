package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/6
 */
public final class Message<T> {

    private MessageType type;

    /**
     * 消息内容
     */
    private T data;
}
