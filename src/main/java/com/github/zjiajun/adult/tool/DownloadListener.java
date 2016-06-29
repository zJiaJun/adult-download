package com.github.zjiajun.adult.tool;

/**
 * Created by zhujiajun
 * 16/6/29 09:22
 *
 * 下载回调
 */
public interface DownloadListener {

    default void success() {
        //nothing
    }

    default void failure(AdultDownInfo downInfo,Exception e) {
        System.err.println("Download url wrong : " + downInfo.getUrl());
        //可以把失败的url保存到集合里,后续继续处理
    }
}
