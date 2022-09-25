package com.github.zjiajun.adult.event.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 21:37
 */
@Data
@AllArgsConstructor
public class PersistentEvent {

    private final String fileName;

    private final String downloadUrl;
}
