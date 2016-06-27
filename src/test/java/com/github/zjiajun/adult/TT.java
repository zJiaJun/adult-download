package com.github.zjiajun.adult;

import com.github.zjiajun.adult.tool.AdultConfig;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.github.zjiajun.adult.tool.AdultTool.connectAndParse;
import static com.github.zjiajun.adult.tool.AdultTool.randomUa;

/**
 * Created by zhujiajun
 * 16/6/27 10:41
 */
public class TT {

    public static void main(String[] args) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://www.rosixz.com/photo/320/rosimm-320-001.jpg").openConnection();
        connection.addRequestProperty("User-Agent", randomUa());
        connection.connect();
        DownloadUtils.download(connection.getInputStream(), AdultConfig.picDownloadPath(),"123");

    }
}
