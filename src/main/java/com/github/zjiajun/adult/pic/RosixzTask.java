package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.DownloadUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.github.zjiajun.adult.tool.AdultTool.*;
import static com.github.zjiajun.adult.tool.AdultConfig.*;
/**
 * Created by zhujiajun
 * 16/6/26 20:43
 */
public class RosixzTask implements Runnable {

    private final String pageUrl;

    public RosixzTask(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public void run() {
        //TODO 需要重构
        Document pageDoc = connectAndParse(pageUrl);
        Elements elements = pageDoc.select(".photo a[href]");
        System.out.println("Total pic size : " + elements.size());
        for (Element element : elements) {
            String detailUrl = element.absUrl("href");
            Element childElement = element.child(0);
            String detailName = childElement.attr("alt");
            String path = picDownloadPath() + detailName + File.separator;
            File file = new File(path);
            if (!file.exists() && file.mkdirs()) {
                Document detailDocument = connectAndParse(detailUrl);
                Elements detailElements = detailDocument.select(".imglist a[href]");
                System.out.println("Detail total pic size : " + detailElements.size());
                for (Element detailElement : detailElements) {
                    String detailImgUrl = detailElement.absUrl("href");
                    String detailImgName = detailImgUrl.substring(detailImgUrl.indexOf("-") + 1, detailImgUrl.length());
                    if (!"http://www.rosixz.com/photo/194/rosimm-194-006.jpg".equals(detailImgUrl)) {
                        URLConnection uc = null;
                        try {
                            uc = new URL(detailImgUrl).openConnection();
                            uc.addRequestProperty("User-Agent",randomUa());
                            uc.connect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        InputStream inputStream = null;
                        try {
                            inputStream = uc.getInputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Start download : " + detailImgUrl);
                        DownloadUtils.download(inputStream, path, detailImgName);
                        System.out.println("finished download : " + detailImgName);
                    }
                }
            }
        }


    }

}
