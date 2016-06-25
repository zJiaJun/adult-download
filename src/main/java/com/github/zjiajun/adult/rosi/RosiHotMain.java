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
    private static final String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    public static void main(String[] args) {
        try {
            for (int page = 1; page <= 16 ; page++) {
                Document document = Jsoup.connect("http://www.rosixz.com/page-" + page + ".html")
                        .userAgent(UA).get();
                Elements elements = document.select(".photo a[href]");
                System.out.println("Total pic size : " + elements.size());
                for (Element element : elements) {
                    String detailUrl = element.absUrl("href");
                    Element childElement = element.child(0);
                    String detailName = childElement.attr("alt");
                    String path = DOWNLOAD_PATH + detailName + File.separator;
                    File file = new File(path);
                    if (!file.exists() && file.mkdirs()) {
                        Document detailDocument = Jsoup.connect(detailUrl).userAgent(UA).get();
                        Elements detailElements = detailDocument.select(".imglist a[href]");
                        System.out.println("Detail total pic size : " + detailElements.size());
                        for (Element detailElement : detailElements) {
                            String detailImgUrl = detailElement.absUrl("href");
                            String detailImgName = detailImgUrl.substring(detailImgUrl.indexOf("-") + 1, detailImgUrl.length());
                            if (!"http://www.rosixz.com/photo/194/rosimm-194-006.jpg".equals(detailImgUrl)) {
                                URLConnection uc = new URL(detailImgUrl).openConnection();
                                uc.addRequestProperty("User-Agent",UA);
                                uc.connect();
                                InputStream inputStream = uc.getInputStream();
                                System.out.println("Start download : " + detailImgUrl);
                                DownloadUtils.download(inputStream, path, detailImgName);
                                System.out.println("finished download : " + detailImgName);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
