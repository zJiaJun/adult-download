package com.github.zjiajun.adult.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhujiajun
 * @since 2017/4/10
 */
@Data
@Builder
public class Response {

    private String contentType;
    private String content;
    private byte[] bytes;
    private int statusCode;
    private String fileName;


    public boolean isTextHtml() {
        return "text/html".equals(contentType);
    }


}
