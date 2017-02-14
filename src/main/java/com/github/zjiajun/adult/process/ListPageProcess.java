package com.github.zjiajun.adult.process;

import com.github.zjiajun.adult.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/2/13
 *
 * 帖子列表页解析处理
 */
public class ListPageProcess implements Process {

    @Override
    public void process(Page page) {
        Document listPageDoc = Jsoup.parse(page.getHtml());
        Elements elements = listPageDoc.select("table[id^=forum]:contains(推荐主题) span a");
        Map<String, String> urlMap = new LinkedHashMap<>();
        elements.forEach(e -> {
            String text = e.text();//a标签内容,即title
            String href = e.absUrl("href");//详情url
            String filePath = text.replaceAll("/", "");//替换斜杠，防止新建多层目录
            urlMap.put(filePath, href);
        });

    }


}
