package com.github.zjiajun.adult.pic;

import com.github.zjiajun.adult.connection.ConnectionRequest;
import com.github.zjiajun.adult.tool.ThreadPoolTool;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static com.github.zjiajun.adult.tool.AdultConfig.*;
import static com.github.zjiajun.adult.connection.AdultConnection.*;


/**
 * Created by zhujiajun
 * 16/6/25 21:56
 */
public class PicApp {

    private static final int POOL_SIZE = 6;

    public void handlerRosixz() {
        Map<Integer, String> pageInfo = getRosixzPageInfo();
        ExecutorService executor = ThreadPoolTool.getInstance().getExecutor("pic-rosixz-pool", POOL_SIZE, pageInfo.size());
        pageInfo.values().forEach(url -> executor.execute(new RosixzTask(url)));
        ThreadPoolTool.getInstance().shutDown("pic-rosixz-pool");
    }

    /**
     * 获取rosixz的分页信息
     * @return Map[page->pageUrl]
     */
    private Map<Integer,String> getRosixzPageInfo() {
        ConnectionRequest request = new ConnectionRequest.Builder()
                .url(rosixzUrl()).userAgent(randomUA())
                .sleep(true).sleepSeconds(5).build();
        Document indexDoc = connect(request);
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

}
