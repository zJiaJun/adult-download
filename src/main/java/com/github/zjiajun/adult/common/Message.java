package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/6
 */
public final class Message<T> {

    //TODO 改为枚举，表示消息类型
    private int type;

    /**
     * 消息内容
     */
    private T data;
}
