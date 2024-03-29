package com.github.zjiajun.adult.parser;

import com.github.zjiajun.adult.model.SiteData;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2023/1/1 19:00
 */
public interface PageParser {

    void parse(Document document, SiteData siteData);
}
