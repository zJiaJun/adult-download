package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/4
 */
public interface Producer<T> {

    void send(T t);
}
