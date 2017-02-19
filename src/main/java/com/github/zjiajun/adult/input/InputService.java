package com.github.zjiajun.adult.input;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.Request;
import com.github.zjiajun.adult.connection.AbstractConnection;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author zhujiajun
 * @since 2017/2/3
 */
public class InputService extends AbstractConnection implements Input {

    @Override
    protected void beforeConnection(Request request) {

    }

    @Override
    protected void exceptionCaught(Request request, Exception exception) {

    }

    @Override
    public Page input(Request request) {
        return super.connect(request);
    }

    @Override
    protected void afterConnection(Request request, ResponseBody responseBody, Page page) throws IOException {
        /* 不能使用body.string()方法
         * 返回ContentType头为text/html，没有指明charset
         * 使用默认的UTF-8，会出现乱码
         * 自定义GBK字符
         */
        String html = new String(responseBody.bytes(), Charset.forName("GBK"));
        page.setHtml(html);
        page.setRequest(request);
    }
}
