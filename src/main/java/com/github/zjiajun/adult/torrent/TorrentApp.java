package com.github.zjiajun.adult.torrent;

import com.github.zjiajun.adult.tool.AdultRequestInfo;
import com.github.zjiajun.adult.tool.ConnectionListener;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

    private final Map<String,String> headers;//请求头
    private final Map<String,String> cookiesContext;//登陆后保存的cookies上下文
    private final String UA;//访问需要登陆权限的页面,必须和登陆的ua保持一致,似乎服务端有验证

    public TorrentApp() {
        headers = new HashMap<>();
        cookiesContext = new HashMap<>();
        UA = randomUa();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, sdch");
        headers.put("Connection", "keep-alive");
        headers.put("Host", sexInSexHost());
    }

    public void handlerSexInSex() {
        loginSexInSex();
        getThreadList();
    }

    public static void main(String[] args) {
        TorrentApp app = new TorrentApp();
        app.handlerSexInSex();

    }

    private void loginSexInSex() {
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

        AdultRequestInfo requestInfo = new AdultRequestInfo.Builder()
                .url(sexInSexLoginUrl()).headers(headers).userAgent(UA).data(loginReqData)
                .method("POST").build();

         connect(requestInfo, new ConnectionListener() {
            @Override
            public void success(Connection.Response response) {
                cookiesContext.putAll(response.cookies());
            }
        });

    }


    private Map<String,String> getThreadList() {
        AdultRequestInfo requestInfo = new AdultRequestInfo.Builder()
                .url(sexInSexAsiaYouUrl()).headers(headers).userAgent(UA)
                .cookies(cookiesContext).build();

        Document threadDoc = connect(requestInfo);
        //排除版块中的固定主題和重要主題，区分置顶贴和实际内容贴，有更好的区分方式?
        Elements elements = threadDoc.select("table[id^=forum]:contains(推荐主题) span a");
        Map<String,String> urlMap = new LinkedHashMap<>();
        elements.forEach(e -> {
            String text = e.text();//a标签内容,即title
            String href = e.absUrl("href");//详情url
            String filePath = text.replaceAll("/", "");//替换斜杠，防止新建多层目录
            urlMap.put(filePath,href);

        });
        System.out.println(urlMap);
        return urlMap;
    }

}
