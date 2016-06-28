package com.github.zjiajun.adult.torrent;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.tool.AdultTool.*;
/**
 * Created by zhujiajun
 * 16/6/28 13:52
 */
public class TorrentApp {

    private final Map<String,String> cookiesContext = new HashMap<>();
    private final String ua = randomUa();//后续访问页面的ua 必须和登陆的ua保持一致,似乎服务端有验证

    public void handlerSexInSex() {
        loginSexInSex();
        getThreadList();
    }

    public static void main(String[] args) {
        TorrentApp app = new TorrentApp();
        app.handlerSexInSex();

    }

    private void loginSexInSex() {
        try {
            Map<String,String> loginReqData = new HashMap<>();
//            loginReqData.put("formhash",loginFormHash());
            loginReqData.put("referer","index.php");
            loginReqData.put("loginfield","username");
            loginReqData.put("username",sexInSexUserName());
            loginReqData.put("password",sexInSexPassword());
            loginReqData.put("questionid","0");
            loginReqData.put("answer","");
            loginReqData.put("cookietime","2592000");
            loginReqData.put("loginmode","");
            loginReqData.put("styleid","");
            loginReqData.put("loginsubmit","true");

            Connection.Response response = Jsoup.connect(sexInSexLoginUrl()).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Connection", "keep-alive")
                    .header("Host", sexInSexHost())
                    .userAgent(ua).timeout(5000).data(loginReqData).postDataCharset("UTF-8").method(Connection.Method.POST).execute();
            cookiesContext.putAll(response.cookies());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Map<String,String> getThreadList() {
        try {
            Document threadDoc = Jsoup.connect(sexInSexAsiaYouUrl()).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                    .header("Connection", "keep-alive")
                    .header("Host", sexInSexHost())
                    .userAgent(ua).timeout(5000).postDataCharset("UTF-8").cookies(cookiesContext).get();
            //排除版块中的固定主題和重要主題，区分置顶贴和实际内容贴，有更好的区分方式?
            Elements elements = threadDoc.select("table[id^=forum]:contains(推荐主题) span a");
            Map<String,String> urlMap = new LinkedHashMap<>();
            elements.forEach(e -> {
                String text = e.text();//a标签内容,即title
                String href = e.absUrl("href");//详情url
                String filePath = text.replaceAll("/", "");//替换斜杠，防止新建多层目录
                urlMap.put(filePath,href);

            });
            System.out.println(urlMap.size());
            return urlMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
