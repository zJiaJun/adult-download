package com.github.zjiajun.adult.tool;

/**
 * Created by zhujiajun
 * 16/6/27 15:24
 *
 * 下载相关信息 建造者模式
 */
public final class AdultDownInfo {

    private final String url;
    private final String filePath;
    private final String fileName;

    private AdultDownInfo(String url, String filePath, String fileName) {
        this.url = url;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public static class Builder {

        private String url;
        private String filePath;
        private String fileName;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public AdultDownInfo build() {
            return new AdultDownInfo(url,filePath,fileName);
        }

    }

    public String getUrl() {
        return url;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

}
