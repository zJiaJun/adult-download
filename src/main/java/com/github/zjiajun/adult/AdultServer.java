package com.github.zjiajun.adult;

import com.github.zjiajun.adult.event.EventBus;
import com.github.zjiajun.adult.http.RetrofitClient;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/14 22:55
 */
@Slf4j
public class AdultServer {


    public static void run() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info("[Adult Server]---begin initialize http client");
        RetrofitClient.init();
        stopwatch.stop();
        log.info("[Adult Server]---finish initialize http client,take {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

        log.info("[Adult Server]---begin initialize event bus");
        stopwatch.start();
        EventBus.init();
        stopwatch.stop();
        log.info("[Adult Server]---finish initialize event bus, take {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
