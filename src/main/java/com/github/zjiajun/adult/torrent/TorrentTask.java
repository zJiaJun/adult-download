package com.github.zjiajun.adult.torrent;

import com.github.zjiajun.adult.connection.ConnectionRequest;
import com.github.zjiajun.adult.download.AdultDownload;
import com.github.zjiajun.adult.download.DownloadRequest;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.connection.AdultConnection.*;
import static com.github.zjiajun.adult.SiteContext.*;

/**
 * Created by zhujiajun
 * 16/6/30 10:57
 */
public class TorrentTask implements Runnable {

    private final String filePath;
    private final String threadUrl;

    public TorrentTask(String filePath, String threadUrl) {
        this.filePath = torrentDownloadPath() + filePath + File.separator;
        this.threadUrl = threadUrl;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        Document threadDoc = parserThreadUrl();
        parserThreadImg(threadDoc);
        parserThreadAttach(threadDoc);
    }

    private Document parserThreadUrl() {
        ConnectionRequest threadRequest = new ConnectionRequest.Builder()
                .url(threadUrl).headers(getHeaders()).userAgent(getUA())
                .cookies(getCookies()).build();
        return connect(threadRequest);
    }

    private void parserThreadImg(Document threadDoc) {
        //TODO 图片第一张是https 访问下载错误 bug
        Elements imgElements = threadDoc.select("div.t_msgfont img[src^=http]");
        if (imgElements.size() <= 0) return;
        String imgUrl = imgElements.first().attr("src");
        String imgFileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1, imgUrl.length());
        download(imgUrl,imgFileName);
    }

    private void parserThreadAttach(Document threadDoc) {
        Elements attachElements = threadDoc.select("dl.t_attachlist a[href^=attachment]");
        if (attachElements.size() <= 0) return;
        String attachUrl = attachElements.first().absUrl("href");
        String attachName = attachElements.first().text();
        download(attachUrl,attachName);
    }

    private void download(String url,String fileName) {
        DownloadRequest downloadRequest = new DownloadRequest.Builder()
                .url(url).headers(getHeaders())
                .userAgent(getUA()).cookies(getCookies())
                .filePath(filePath).fileName(fileName).build();
        AdultDownload.down2File(downloadRequest);
    }

}
