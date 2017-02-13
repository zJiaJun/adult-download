package com.github.zjiajun.adult.process;

import com.github.zjiajun.adult.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author zhujiajun
 * @since 2017/2/13
 *
 * 帖子列表页解析处理
 */
public class ListPageProcess implements Process {

    @Override
    public void process(Page page) {
        Document document = Jsoup.parse(page.getHtml());

    }


}
