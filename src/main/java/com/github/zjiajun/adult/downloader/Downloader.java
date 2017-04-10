package com.github.zjiajun.adult.downloader;

import com.github.zjiajun.adult.request.Request;

/**
 * @author zhujiajun
 * @since 2017/4/10
 */
public interface Downloader {


    void download(Request request);

}
