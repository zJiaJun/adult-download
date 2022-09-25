package com.github.zjiajun.adult.event.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 21:19
 */
@Data
@AllArgsConstructor
public class ParserEvent {

    private final Document document;
}
