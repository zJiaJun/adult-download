package com.github.zjiajun.adult.model;

import com.github.zjiajun.adult.constatns.RequestMethod;
import com.github.zjiajun.adult.constatns.RequestType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
@Data
@Builder
public class Request {

    private String url;
    private RequestType requestType;
    @Builder.Default
    private RequestMethod requestMethod = RequestMethod.GET;
    @Builder.Default
    private String charset = "UTF-8";
    @Builder.Default
    private Map<String, String> formData = new HashMap<>();
    @Builder.Default
    private Map<String, String> extraData = new HashMap<>();

}
