package com.github.zjiajun.adult;

import com.github.zjiajun.adult.event.EventBus;
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
        RetrofitClient.init();
        EventBus.init();
        log.info("Adult server finish");
    }
}
