package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/8
 */
public class DefaultProducer implements Producer {

    @Override
    public void send(Message message) {
        MessageQueue.put(message);
    }
}
