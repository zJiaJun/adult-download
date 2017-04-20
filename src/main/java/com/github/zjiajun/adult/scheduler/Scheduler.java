package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.response.Response;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public interface Scheduler {

    void putRequest(Request request);

    Request takeRequest();

    void putResponse(Response response);

    Response takeResponse();

}
