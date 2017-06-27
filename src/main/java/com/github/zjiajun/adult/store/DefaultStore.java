package com.github.zjiajun.adult.store;

/**
 * @author zhujiajun
 * @since 2017/6/20
 */
public class DefaultStore implements Store {

    @Override
    public void store(StoreResult storeResult) {
        System.out.println(storeResult);
    }


}
