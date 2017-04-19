package com.github.zjiajun.adult;

import com.github.zjiajun.adult.downloader.DefaultDownloader;
import com.github.zjiajun.adult.downloader.Downloader;
import com.github.zjiajun.adult.manager.DefaultScheduler;
import com.github.zjiajun.adult.manager.Scheduler;
import com.github.zjiajun.adult.request.LoginParamBuild;
import com.github.zjiajun.adult.request.Method;
import com.github.zjiajun.adult.request.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult extends Thread {

    private Adult () {}

    private Request loginRequest;
    private List<Request> requests = new ArrayList<>();
    private Scheduler scheduler;
    private Downloader downloader;
    private int downloadThreadCount;

    public Adult login(String loginUrl, LoginParamBuild loginParamBuild) {
        Map<String,String> param = new HashMap<>(50);
        loginParamBuild.param(param);
        this.loginRequest = new Request.Builder().url(loginUrl).charset("GBK").login().data(param).method(Method.POST).build();
        return this;
    }


    public Adult url(String url) {
        requests.add(new Request.Builder().url(url).charset("GBK").build());
        return this;
    }

    public Adult downloadThreadCount(int downloadThreadCount) {
        this.downloadThreadCount = downloadThreadCount;
        return this;
    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        if (requests.isEmpty()) {
            throw new RuntimeException();
        }

        init();

        if (null != loginRequest) {
            scheduler.put(loginRequest);
        }

    }


    private void init() {
        if (null == scheduler) {
            scheduler = new DefaultScheduler();
        }
        if (null == downloader) {
            downloader = new DefaultDownloader(scheduler);
        }
    }


    public static void main(String [] args) {

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
        });

        /*
        Request loginRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/logging.php?action=login")
                .login()
                .data(loginReqData)
                .build();

        Request pageRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/forum-230-1.html").build();
        */


    }


}
