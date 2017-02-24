package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/6
 */
public final class Message<T> {

    /**
     * 消息类型
     */
    private MessageEnum type;

    /**
     * 消息内容
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp;

    public Message(MessageEnum type, T data) {
        this.type = type;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public Message(MessageEnum type, T data, long timestamp) {
        this.type = type;
        this.data = data;
        this.timestamp = timestamp;
    }

    public MessageEnum getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
