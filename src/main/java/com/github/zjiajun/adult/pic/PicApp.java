package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.exception.AdultException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.tool.AdultTool.*;

/**
 * Created by zhujiajun
 * 16/6/25 21:56
 */
public class PicApp {


    public void handlerRosixz() {
    }

    /**
     * 获取rosixz的总页数
     * @return 总页数
     */
    private int getRosixzPageTotal() {
        try {
            Document indexDoc = Jsoup.connect(rosixzUrl()).userAgent(randomUa()).get();
            Elements pageElements = indexDoc.select(".page-navigator");
            Element element = pageElements.get(pageElements.size() - 1);
            return 1;
        } catch (IOException e) {
            throw new AdultException("Handler rosixz exception : " + e.getMessage());
        }
    }
}
