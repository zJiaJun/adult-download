package com.github.zjiajun.adult.response;

/**
 * @author zhujiajun
 * @since 2017/4/10
 */
public class Response {

    private String content;

    private int statusCode;

    public Response(String content, int statusCode) {
        this.content = content;
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
