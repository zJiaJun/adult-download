package com.github.zjiajun.adult.store;

import com.github.zjiajun.adult.request.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/6/21
 */
public class StoreResult {


    private StoreResult() {}

    private boolean isSubUrl = false;
    private List<Request> subRequestList = new ArrayList<>();

    public static class Builder {

        private StoreResult storeResult = new StoreResult();

        public Builder subUrl(Request subRequest) {
            storeResult.isSubUrl = true;
            storeResult.subRequestList.add(subRequest);
            return this;
        }


        public StoreResult build() {
            return storeResult;
        }
    }

    public boolean isSubUrl() {
        return isSubUrl;
    }
}
