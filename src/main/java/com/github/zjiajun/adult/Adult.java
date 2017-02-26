package com.github.zjiajun.adult;

import com.github.zjiajun.adult.input.Input;
import com.github.zjiajun.adult.input.InputService;
import com.github.zjiajun.adult.process.ListPageProcess;
import com.github.zjiajun.adult.process.Process;
import com.github.zjiajun.adult.tool.ThreadPoolTool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author zhujiajun
 * @since 2017/1/31
 *
 * 主类
 */
public class Adult {

    private Adult () {}

    private Request loginRequest;
    private Request pageRequest;

    private final Input input = new InputService();
    private final Process process = new ListPageProcess();

    public static class Builder {

        private Adult adult = new Adult();

        public Builder login(Request loginRequest) {
            adult.loginRequest = loginRequest;
            return this;
        }

        public Builder page(Request pageRequest) {
            adult.pageRequest = pageRequest;
            return this;
        }

        public Adult build() {
            return adult;
        }
    }


    private final ExecutorService inputExecutor = ThreadPoolTool.getInstance().getExecutor("input", 4, 100);
    private final ExecutorService processExecutor = ThreadPoolTool.getInstance().getExecutor("process",8,500);


    public void run() {
        if (null != loginRequest) {
            //do login
            input.input(loginRequest);
        }

        Page page = this.input.input(pageRequest);
        PageResult pageResult = this.process.process(page);


    }


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
                .post()
                .build();

        Request pageRequest = new Request.Builder()
                .url("http://67.220.90.4/forum/forum-230-1.html")
                .get().build();

        Adult adult = new Adult.Builder().login(loginRequest).page(pageRequest).build();

    }


}
