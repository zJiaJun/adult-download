package com.github.zjiajun.adult;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujiajun
 * 15/5/14 21:28
 */
public class HtmlParser {


    public static String formHash(String html) {
        Document indexDoc = Jsoup.parse(html);
        Elements select = indexDoc.select("input[name=formhash]");
        return select.attr("value");
    }

    public static Map<String,String> thread(String html) {
        Document document = Jsoup.parse(html,HttpClientDemo.URL_PREFIX);
        //排除版块中的固定主題和重要主題，区分置顶贴和实际内容贴，有更好的区分方式?
        Elements elements = document.select("table[id^=forum]:contains(推荐主题) span a");
        Map<String,String> urlMap = new LinkedHashMap<>();
        for (Element element : elements) {
            String text = element.text();//a标签内容,即title
            String href = element.absUrl("href");//post详情url
            String filePath = text.replaceAll("/", "");//替换斜杠，防止新建多层目录
            urlMap.put(filePath,href);

        }
        return  urlMap;
    }

    public static Map<String,String> attachment(String html) {
        Document document = Jsoup.parse(html,HttpClientDemo.URL_PREFIX);
        Elements attachElements = document.select("dl.t_attachlist a[href^=attachment]");
        Map<String,String> attachMap = new LinkedHashMap<>();
        for (Element attachElement : attachElements) {
            String attachName = attachElement.text();
            String attachUrl = attachElement.absUrl("href");//种子附件下载地址
            attachMap.put(attachName,attachUrl);
        }
        return attachMap;
    }

    public static Map<String,String> img(String html) {
        Document document = Jsoup.parse(html);
        Elements imgElements = document.select("div.t_msgfont img[src^=http]");
        Map<String,String> imgMap = new LinkedHashMap<>();
        for (Element imgElement : imgElements) {
            //TODO 图片第一张是https 访问下载错误 bug
            String imgUrl = imgElement.attr("src");
            String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1, imgUrl.length());
            imgMap.put(imgFileName,imgUrl);
            break;//图片太多，只抽取帖子第一张图片，多数是封面
        }
        return imgMap;
    }

}
