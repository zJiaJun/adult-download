package com.github.zjiajun.adult;

import com.github.zjiajun.adult.manager.DefaultScheduler;
import com.github.zjiajun.adult.manager.Scheduler;
import com.github.zjiajun.adult.request.LoginParamBuild;

import java.util.ArrayList;
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

    private String loginUrl;
    private Map<String,String> loginParam;
    private List<String> urls = new ArrayList<>();
    private Scheduler scheduler;

    public Adult login(String loginUrl, LoginParamBuild loginParamBuild) {
        this.loginUrl = loginUrl;
        loginParamBuild.param(loginParam);
        return this;
    }



    public Adult url(String url) {
       urls.add(url);
       return this;
    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        if (urls.isEmpty()) {
            throw new RuntimeException();
        }

        if (null == scheduler) {
            scheduler = new DefaultScheduler();

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
