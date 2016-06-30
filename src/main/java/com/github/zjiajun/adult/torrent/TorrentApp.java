package com.github.zjiajun.adult.torrent;

import com.github.zjiajun.adult.SiteContext;
import com.github.zjiajun.adult.connection.ConnectionListener;
import com.github.zjiajun.adult.connection.ConnectionRequest;
import com.github.zjiajun.adult.tool.*;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.connection.AdultConnection.*;
import static com.github.zjiajun.adult.SiteContext.*;
/**
 * Created by zhujiajun
 * 16/6/28 13:52
 */
public class TorrentApp {

    public TorrentApp() {
        SiteContext.initSexInSexHeaders();
    }

    public void handlerSexInSex() {
        loginSexInSex();
        Map<String, String> threadList = getThreadList();
        ExecutorService executor = ThreadPoolTool.getInstance().getExecutor("torrent-sexinsex-pool", 6);
        threadList.forEach((k,v)-> executor.execute(new TorrentTask(k,v)));
    }

    /**
     * 登录回写cookies
     */
    private void loginSexInSex() {
        Map<String,String> loginReqData = new HashMap<>();
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

        ConnectionRequest loginRequest = new ConnectionRequest.Builder()
                .url(sexInSexLoginUrl()).headers(getHeaders()).userAgent(getUA()).data(loginReqData)
                .method("POST").build();

         connect(loginRequest, new ConnectionListener() {
            @Override
            public void success(Connection.Response response) {
                SiteContext.setCookies(response.cookies());
            }
        });

    }

    /**
     * 获取帖子列表
     * @return Map[filePath->href]
     */
    private Map<String,String> getThreadList() {
        ConnectionRequest threadRequest = new ConnectionRequest.Builder()
                .url(sexInSexAsiaYouUrl()).headers(getHeaders()).userAgent(getUA())
                .cookies(getCookies()).build();
        Document threadListDoc = connect(threadRequest);
        //排除版块中的固定主題和重要主題，区分置顶贴和实际内容贴，有更好的区分方式?
        Elements elements = threadListDoc.select("table[id^=forum]:contains(推荐主题) span a");
        Map<String, String> urlMap = new LinkedHashMap<>();
        elements.forEach(e -> {
            String text = e.text();//a标签内容,即title
            String href = e.absUrl("href");//详情url
            String filePath = text.replaceAll("/", "");//替换斜杠，防止新建多层目录
            urlMap.put(filePath, href);
        });
        return urlMap;
    }

}
