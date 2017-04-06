package com.github.zjiajun.adult;

import com.github.zjiajun.adult.request.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    private Adult () {}



    public static void main(String [] args) {
        Map<String,String> loginReqData = new HashMap<>();
        loginReqData.put("referer","index.php");
        loginReqData.put("loginfield","username");
        loginReqData.put("username","leoneye");
        loginReqData.put("password","wqcez136");
        loginReqData.put("questionid","0");
        loginReqData.put("answer","");
        loginReqData.put("cookietime","315360000");
        loginReqData.put("loginmode","");
        loginReqData.put("styleid","");
        loginReqData.put("loginsubmit","true");
        Request loginRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/logging.php?action=login")
                .login()
                .data(loginReqData)
                .build();

        Request pageRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/forum-230-1.html")
                .build();


    }


}
