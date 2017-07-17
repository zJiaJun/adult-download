package com.github.zjiajun.adult;

import com.github.zjiajun.adult.config.Config;

/**
 * @author zhujiajun
 * @since 2017/7/17
 */
public class Main {

    public static void main(String[] args) {
        new Adult().login(Config.getInstance().loginUrl(), param -> {
            param.put("referer","index.php");
            param.put("loginfield","username");
            param.put("username", Config.getInstance().userName());
            param.put("password", Config.getInstance().password());
            param.put("questionid","0");
            param.put("answer","");
            param.put("cookietime","315360000");
            param.put("loginmode","");
            param.put("styleid","");
            param.put("loginsubmit","true");
        }).url(Config.getInstance().asiaYouUrl()).start();
    }
}
