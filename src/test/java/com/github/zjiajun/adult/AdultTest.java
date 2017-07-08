package com.github.zjiajun.adult;

import com.github.zjiajun.adult.config.Config;
import com.github.zjiajun.adult.downloader.RetrofitClient;
import com.github.zjiajun.adult.request.Request;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @since 2017/6/11
 */
public class AdultTest {

    @Test
    public void testAdult() throws InterruptedException {
        new Adult().login("http://67.220.90.4/forum/logging.php?action=login", param -> {
            param.put("referer","index.php");
            param.put("loginfield","username");
            param.put("username","leoneye");
            param.put("password","wqcez136");
            param.put("questionid","0");
            param.put("answer","");
            param.put("cookietime","315360000");
            param.put("loginmode","");
            param.put("styleid","");
            param.put("loginsubmit","true");
        }).url("http://67.220.90.4/forum/forumdisplay.php?fid=230").start();
        TimeUnit.DAYS.sleep(1);
    }

    @Test
    public void testRetrofit() throws IOException {
        RetrofitClient.Api api = RetrofitClient.getInstance().getApi();
        Response<ResponseBody> execute = api.get("forumdisplay.php?fid=230").execute();
        String html = new String(execute.body().bytes(), "GBK");
        Document document = Jsoup.parse(html, Config.getInstance().baseUrl());

        Elements elements = document.select("table[id^=forum]:contains(版块主题) span a");
        elements.forEach(e -> {
            String href = e.absUrl("href");
            System.out.println(href);
        });
    }

    @Test
    public void testWriteFile() throws IOException {
//        Request build = new Request.Builder().url("http://67.220.90.4/forum/attachment.php?aid=3105998").build();
        Request build = new Request.Builder().url("http://img588.net/images/2017/06/29/javcc.net_bcdp087pld7e1f.jpg").build();
        com.github.zjiajun.adult.response.Response execute = RetrofitClient.getInstance().execute(build);
        byte[] bytes = execute.getBytes();
        //信息: Content-Disposition: attachment; filename="m20170629-28.torrent"

        java.nio.file.Files.write(Paths.get("/Users/zhujiajun/Work/1.jpg"), bytes, StandardOpenOption.APPEND);

//        Files.write(bytes,new File("/Users/zhujiajun/Work/1.torrent"));
    }
}
