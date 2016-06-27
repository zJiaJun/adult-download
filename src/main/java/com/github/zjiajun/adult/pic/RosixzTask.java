package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.DownloadUtils;
import com.github.zjiajun.adult.exception.AdultException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

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
        try {
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
                            URLConnection uc = new URL(detailImgUrl).openConnection();
                            uc.addRequestProperty("User-Agent", randomUa());
                            uc.connect();
                            InputStream inputStream = uc.getInputStream();
                            System.out.println("Start download : " + detailImgUrl);
                            DownloadUtils.download(inputStream, path, detailImgName);
                            System.out.println("finished download : " + detailImgName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AdultException("Handler " + pageUrl  + e.getMessage());
        }
    }

    private void doWork() {
        Map<String, String> detailInfo = getDetailInfoByPageUrl(pageUrl);
        detailInfo.forEach(this::getPicInfoByDetailUrl);
    }

    private void getPicInfoByDetailUrl(String detailUrl,String detailContent) {
        String downloadPath = picDownloadPath() + detailContent + File.separator;
        File file = new File(downloadPath);
        if (!file.mkdirs()) return;
        Document detailDoc = connectAndParse(detailUrl);
        Elements detailElements = detailDoc.select(".imglist a[href]");
        detailElements.forEach(e -> downloadDetailImg(e.absUrl("href"),downloadPath));
    }

    private void downloadDetailImg(String detailImgUrl,String downloadPath) {
        try {
            String detailImgName = detailImgUrl.substring(detailImgUrl.indexOf("-") + 1, detailImgUrl.length());
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(detailImgUrl).openConnection();
            urlConnection.addRequestProperty("User-Agent", randomUa());
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            DownloadUtils.download(inputStream, downloadPath, detailImgName);
        } catch (Exception e) {
            throw new AdultException("");
        }
    }


    /**
     * 获取当前页中的列表详细信息,包含图片详细页url,和图片详细说明
     * @param pageUrl 当前页
     * @return Map[detailUrl->detailContent]
     */
    private Map<String,String> getDetailInfoByPageUrl(String pageUrl) {
        Map<String,String> detailMap = new HashMap<>();
        Document pageDoc = connectAndParse(pageUrl);
        Elements detailElements = pageDoc.select(".photo a[href]");
        detailElements.forEach(e -> {
            String detailUrl = e.absUrl("href");
            String detailContent = e.select("[alt]").attr("alt");
            detailMap.put(detailUrl,detailContent);
        });
        return detailMap;
    }



}
