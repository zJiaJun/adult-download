package com.github.zjiajun.adult;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public class Page {

    private Request request;

    private String html;

    private int statusCode;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
