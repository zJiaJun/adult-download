package com.github.zjiajun.adult;

import org.junit.Test;

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
}
