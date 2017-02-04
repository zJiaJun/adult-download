package com.github.zjiajun.adult.input;

import com.github.zjiajun.adult.Adult;
import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.AbstractConnection;
import okhttp3.ResponseBody;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class InputService extends AbstractConnection implements Input {

    private final Adult adult;

    public InputService(Adult adult) {
        this.adult = adult;
    }

    @Override
    protected void beforeConnection(Request request) {

    }

    @Override
    public Page input(Request request) {
        super.connect(request);
        Page page = new Page();
        return page;
    }

    @Override
    protected void afterConnection(ResponseBody responseBody) {
        /* 不能使用body.string()方法
         * 返回ContentType头为text/html，没有指明charset
         * 使用默认的UTF-8，会出现乱码
         * 自定义GBK字符
         */


    }
}
