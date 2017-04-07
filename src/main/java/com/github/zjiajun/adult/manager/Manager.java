package com.github.zjiajun.adult.manager;

import com.github.zjiajun.adult.request.Request;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public interface Manager {

    void put(Request request);

    Request take();

}
