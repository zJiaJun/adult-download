package com.github.zjiajun.adult.tool;

import java.util.Map;

/**
 * Created by zhujiajun
 * 16/6/27 15:24
 *
 * 下载相关信息
 */
public final class AdultDownInfo extends AdultBasicInfo {

    private String filePath;
    private String fileName;

    private AdultDownInfo() {}

    public static class Builder {
        private AdultDownInfo adultDownInfo = new AdultDownInfo();

        public Builder url(String url) {
            adultDownInfo.setUrl(url);
            return this;
        }

        public Builder userAgent(String userAgent) {
            adultDownInfo.setUserAgent(userAgent);
            return this;
        }

        public Builder headers(Map<String,String> headers) {
            adultDownInfo.setHeaders(headers);
            return this;
        }

        public Builder cookies(Map<String,String> cookies) {
            adultDownInfo.setCookies(cookies);
            return this;
        }

        public Builder filePath(String filePath) {
            adultDownInfo.filePath = filePath;
            return this;
        }

        public Builder fileName(String fileName) {
            adultDownInfo.fileName = fileName;
            return this;
        }

        public AdultDownInfo build() {
            return adultDownInfo;
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

}
