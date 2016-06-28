package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.tool.AdultRequestInfo;
import com.github.zjiajun.adult.tool.ConnectionListener;
import com.github.zjiajun.adult.tool.ThreadPoolTool;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.tool.AdultTool.*;

/**
 * Created by zhujiajun
 * 16/6/25 21:56
 */
public class PicApp {

    //TODO 多线程访问,评率太高,后续访问报异常返回403。待解决,添加代理? 休眠线程访问?
    private static final int POOL_SIZE = 6;

    public void handlerRosixz() {
        Map<Integer, String> pageInfo = getRosixzPageInfo();
        ExecutorService executor = ThreadPoolTool.getInstance().getExecutor("rosixz-pool", POOL_SIZE);
        pageInfo.values().forEach(url -> executor.execute(new RosixzTask(url)));
    }

    /**
     * 获取rosixz的分页信息
     * @return Map[page->pageUrl]
     */
    private Map<Integer,String> getRosixzPageInfo() {
        AdultRequestInfo requestInfo = new AdultRequestInfo.Builder()
                .url(rosixzUrl()).userAgent(randomUa())
                .sleep(true).sleepSeconds(5).build();
        Document indexDoc = connect(requestInfo);
        Elements pageElements = indexDoc.select(".page-navigator li a");
        Element totalElement = pageElements.get(pageElements.size() - 2);//倒数第2个是总页数
        String pageUrl = totalElement.absUrl("href");
        String totalSize = totalElement.html();
        Map<Integer,String> pageInfo = new HashMap<>();
        for (int page = Integer.parseInt(totalSize); page > 0; page--) {
            pageInfo.put(page,pageUrl.replace(totalSize,String.valueOf(page)));
        }
        return pageInfo;
    }

    public static void main(String[] args) {
        PicApp app = new PicApp();
        app.handlerRosixz();
    }
}
