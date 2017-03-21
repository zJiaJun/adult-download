package com.github.zjiajun.adult.process;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.PageResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @since 2017/3/21
 */
public abstract class AbstractProcess implements Process {

    @Override
    public PageResult process(Page page) {
        String orignalHtml = page.getHtml();
        Document document = Jsoup.parse(orignalHtml);
        return null;
    }
}
