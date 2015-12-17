package com.github.zjiajun.adult.rosi;

import com.github.zjiajun.adult.DownloadUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhujiajun
 * 15/12/14 21:40
 */
public class RosiHotMain {

    private static final String DOWNLOAD_PATH = "/Users/zhujiajun/Downloads/rosihot/";

    public static void main(String[] args) {
        try {
            for (int page = 1; page <= 16 ; page++) {
                Document document = Jsoup.connect("http://www.rosihot.com/page-" + page + ".html").get();
                Elements elements = document.select(".photo a[href]");
                System.out.println("Total pic size : " + elements.size());
                for (Element element : elements) {
                    String detailUrl = element.absUrl("href");
                    Element childElement = element.child(0);
                    String detailName = childElement.attr("alt");
                    String path = DOWNLOAD_PATH + detailName + File.separator;
                    File file = new File(path);
                    if (!file.exists() && file.mkdirs()) {
                        Document detailDocument = Jsoup.connect(detailUrl).get();
                        Elements detailElements = detailDocument.select(".imglist a[href]");
                        System.out.println("Detail total pic size : " + detailElements.size());
                        for (Element detailElement : detailElements) {
                            String detailImgUrl = detailElement.absUrl("href");
                            String detailImgName = detailImgUrl.substring(detailImgUrl.indexOf("-") + 1, detailImgUrl.length());
                            URLConnection url = new URL(detailImgUrl).openConnection();
                            url.connect();
                            InputStream inputStream = url.getInputStream();
                            System.out.println("Start download : " + detailImgUrl);
                            DownloadUtils.download(inputStream, path, detailImgName);
                            System.out.println("finished download : " + detailImgName);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
