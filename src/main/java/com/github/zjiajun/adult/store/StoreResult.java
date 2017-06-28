package com.github.zjiajun.adult.store;

import com.github.zjiajun.adult.request.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhujiajun
 * @since 2017/6/21
 */
public class StoreResult {


    private StoreResult() {}

    private boolean isSubUrl = false;
    private List<Request> subRequestList = new ArrayList<>();
    private Map<String,String> resultMap = new ConcurrentHashMap<>();

    public static class Builder {

        private StoreResult storeResult = new StoreResult();

        public Builder subUrl(Request subRequest) {
            storeResult.isSubUrl = true;
            storeResult.subRequestList.add(subRequest);
            return this;
        }

        public Builder result(String name, String url) {
            storeResult.resultMap.put(name, url);
            return this;
        }


        public StoreResult build() {
            return storeResult;
        }
    }

    public boolean isSubUrl() {
        return isSubUrl;
    }

    public List<Request> getSubRequestList() {
        return subRequestList;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }
}
