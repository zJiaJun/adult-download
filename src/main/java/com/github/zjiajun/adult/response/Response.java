package com.github.zjiajun.adult.response;

/**
 * @author zhujiajun
 * @since 2017/4/10
 */
public class Response {

    private String content;

    private byte [] bytes;

    private int statusCode;

    public Response(String content, byte [] bytes, int statusCode) {
        this.content = content;
        this.bytes = bytes;
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }


    public byte[] getBytes() {
        return bytes;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "content='" + content + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
