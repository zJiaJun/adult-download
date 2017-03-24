package com.github.zjiajun.adult.process;

import com.github.zjiajun.adult.Page;
import com.github.zjiajun.adult.PageResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.function.Consumer;

/**
 * @author zhujiajun
 * @since 2017/3/21
 */
public abstract class AbstractProcess implements Process {

    @Override
    public PageResult process(Page page) {
        String originalHtml = page.getHtml();
        Document document = Jsoup.parse(originalHtml);
        String cssQuery = buildCssQuery();
        Elements elements = document.select(cssQuery);
        elements.forEach(this::action);
        return null;
    }

    protected abstract String buildCssQuery();

    protected abstract Consumer action(Element element);
}
