package com.github.zjiajun.adult.common;

/**
 * @author zhujiajun
 * @since 2017/2/4
 */
public interface Consumer<T> {

    T fetch();
}
