package com.github.zjiajun.adult.store;

/**
 * @author zhujiajun
 * @since 2017/6/21
 */
public class StoreResult {


    private StoreResult() {}

    private boolean isSubUrl = false;

    public static class Builder {

        private StoreResult storeResult = new StoreResult();

        public Builder subUrl() {
            storeResult.isSubUrl = true;
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
