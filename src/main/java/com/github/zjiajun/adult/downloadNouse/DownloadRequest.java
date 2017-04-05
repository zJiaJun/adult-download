package com.github.zjiajun.adult.downloadNouse;

import com.github.zjiajun.adult.BasicRequest;

import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/27 15:24
 *
 * 下载相关信息
 */
public final class DownloadRequest extends BasicRequest {

    private String filePath;
    private String fileName;

    private DownloadRequest() {}

    public static class Builder {
        private DownloadRequest downloadRequest = new DownloadRequest();

        public Builder url(String url) {
            downloadRequest.setUrl(url);
            return this;
        }

        public Builder userAgent(String userAgent) {
            downloadRequest.setUserAgent(userAgent);
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            downloadRequest.setHeaders(headers);
            return this;
        }

        public Builder cookies(Map<String,String> cookies) {
            downloadRequest.setCookies(cookies);
            return this;
        }

        public Builder filePath(String filePath) {
            downloadRequest.filePath = filePath;
            return this;
        }

        public Builder fileName(String fileName) {
            downloadRequest.fileName = fileName;
            return this;
        }

        public DownloadRequest build() {
            return downloadRequest;
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

}
