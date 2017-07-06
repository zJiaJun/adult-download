package com.github.zjiajun.adult.store;

import com.github.zjiajun.adult.downloader.RetrofitClient;
import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.response.Response;

import java.io.IOException;
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
            try {
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Request downloadRequest = new Request.Builder().url(value).build();
                    Response execute = retrofitClient.execute(downloadRequest);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
