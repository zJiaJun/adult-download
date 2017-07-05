package com.github.zjiajun.adult.store;

import com.github.zjiajun.adult.downloader.RetrofitClient;

import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/6/20
 */
public class DefaultStore implements Store {

    private final RetrofitClient retrofitClient;

    public DefaultStore() {
        this.retrofitClient = RetrofitClient.getInstance();
    }

    @Override
    public void store(StoreResult storeResult) {
        Map<String, String> resultMap = storeResult.getResultMap();
        if (null != resultMap && !resultMap.isEmpty()) {
            resultMap.forEach((k, v) -> {
                

            });

        }
    }


}
