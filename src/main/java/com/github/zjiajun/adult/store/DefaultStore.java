package com.github.zjiajun.adult.store;

import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/6/20
 */
public class DefaultStore implements Store {

    @Override
    public void store(StoreResult storeResult) {
        System.out.println(storeResult);
        Map<String, String> resultMap = storeResult.getResultMap();
        if (null != resultMap && !resultMap.isEmpty()) {
            resultMap.forEach((k, v) -> {

            });

        }
    }


}
