package com.github.zjiajun.adult.tool;

import com.github.zjiajun.adult.exception.AdultException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.github.zjiajun.adult.tool.AdultConfig.*;

/**
 * Created by zhujiajun
 * 16/6/26 12:25
 *
 * 工具类
 */
public final class AdultTool {


    public static String randomUa() {
        List<String> uaList = uaList();
        Collections.shuffle(uaList);
        return uaList().get(new Random().nextInt(uaList.size()));
    }

    public static Document connectAndParse(String url) {
        try {
            return Jsoup.connect(url).userAgent(randomUa()).timeout(5000).get();
        } catch (IOException e) {
            throw new AdultException(LoggerTool.getTrace(e));
        } finally {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
