package com.github.zjiajun.adult.event.message;

import com.github.zjiajun.adult.model.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 21:10
 */
@Data
@AllArgsConstructor
public class ResponseEvent {

    private final Response response;
}
