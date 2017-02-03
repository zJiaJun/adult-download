package com.github.zjiajun.adult.input;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.AbstractConnection;

/**
 * @author zhujiajun
 * @since 2017/2/1
 */
public abstract class AbstractInput extends AbstractConnection implements Input {

    @Override
    protected void beforeConnect(Request request) {

    }

    @Override
    protected void afterConnect() {

    }

    @Override
    public Page input(Request request) {
        super.connect(request);
        return null;
    }


}
