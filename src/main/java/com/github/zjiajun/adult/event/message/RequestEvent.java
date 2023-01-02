package com.github.zjiajun.adult.event.message;

import com.github.zjiajun.adult.model.Request;
import com.github.zjiajun.adult.model.SiteData;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/15 22:55
 */
@Data
@AllArgsConstructor
public class RequestEvent {

    private final Request request;

    private final SiteData siteData;


}
