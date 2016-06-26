package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.exception.AdultException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.tool.AdultTool.*;

/**
 * Created by zhujiajun
 * 16/6/25 21:56
 */
public class PicApp {


    public void handlerRosixz() {
        Map<Integer, String> pageInfo = getRosixzPageInfo();
        for (String pageUrls: pageInfo.values()) {


        }

    }



    /**
     * 获取rosixz的分页信息
     * @return Map[page->pageUrl]
     */
    private Map<Integer,String> getRosixzPageInfo() {
        try {
            Document indexDoc = Jsoup.connect(rosixzUrl()).userAgent(randomUa()).timeout(5000).get();
            Elements pageElements = indexDoc.select(".page-navigator li a");
            Element totalElement = pageElements.get(pageElements.size() - 2);//倒数第2个是总页数
            String pageUrl = totalElement.absUrl("href");
            String totalSize = totalElement.html();
            Map<Integer,String> pageInfo = new HashMap<>();
            for (int page = Integer.parseInt(totalSize); page > 0; page--) {
                pageInfo.put(page,pageUrl.replace(totalSize,String.valueOf(page)));
            }
            return pageInfo;
        } catch (IOException e) {
            throw new AdultException("Handler rosixz exception : " + e.getMessage());
        }
    }
}
