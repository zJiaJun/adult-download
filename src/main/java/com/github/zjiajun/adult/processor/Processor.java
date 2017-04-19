package com.github.zjiajun.adult.processor;

import com.github.zjiajun.adult.response.Response;

/**
 * @author zhujiajun
 * @since 2017/4/19
 */
public interface Processor {

    void process(Response response);
}
