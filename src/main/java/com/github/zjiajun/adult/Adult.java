package com.github.zjiajun.adult;

import com.github.zjiajun.adult.input.AdultInput;
import com.github.zjiajun.adult.login.AdultLogin;

import java.util.*;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    private boolean needLogin;
    private List<Request> request;

    public static class Builder {

        private Adult adult = new Adult();

        public Builder login() {
            adult.needLogin = true;
            return this;
        }

        public Builder request(List<Request> request) {
            adult.request = request;
            return this;
        }

        public Adult build() {
            return adult;
        }
    }

    public void run() {
        if (needLogin) {
            AdultLogin adultLogin = new AdultLogin();
            adultLogin.login(request.get(0));
        }
        AdultInput adultInput = new AdultInput();
        adultInput.input(request.get(1));

    }


    public static void main(String[] args) {
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
                .data(loginReqData)
                .post()
                .build();

        Request pageRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/forum-230-1.html")
                .get().build();

        List<Request> requests = Arrays.asList(loginRequest, pageRequest);
        new Adult.Builder().login().request(requests).build().run();

    }


}
