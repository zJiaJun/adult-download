package com.github.zjiajun.adult.event.listeners;

import com.github.zjiajun.adult.event.message.RequestEvent;
import com.github.zjiajun.adult.event.message.ResponseEvent;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/11/12 10:45
 */
public interface AdultListener {

    void onRequestSend(RequestEvent requestEvent);

    void onResponseReturn(ResponseEvent responseEvent);

}
