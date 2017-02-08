package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/8
 */
public class DefaultConsumer implements Consumer {

    @Override
    public Message fetch() {
        return MessageQueue.take();
    }
}
