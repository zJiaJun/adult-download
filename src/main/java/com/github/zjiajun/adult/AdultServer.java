package com.github.zjiajun.adult;

import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.event.message.SexInSexEvent;
import com.github.zjiajun.adult.http.RetrofitClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/14 22:55
 */
@Slf4j
public class AdultServer {

    public static void run() {
        log.info("Adult server begin run");
        setUp();
        //TODO 抽象一个公共通用的定义事件的event, 由不同的子类去实现
        EventBus.post(new SexInSexEvent());
        log.info("Adult server finish");
    }

    private static void setUp() {
        EventBus.init();
        RetrofitClient.init();
    }
}
