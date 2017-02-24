package com.github.zjiajun.adult;

import com.github.zjiajun.adult.common.Message;
import com.github.zjiajun.adult.common.MessageEnum;
import com.github.zjiajun.adult.common.MessageQueue;
import com.github.zjiajun.adult.input.Input;
import com.github.zjiajun.adult.input.InputService;
import com.github.zjiajun.adult.tool.ThreadPoolTool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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


    public Adult init() {
        MessageQueue.put(new Message<>(MessageEnum.REQUEST, pageRequest));
        return this;
    }

    private final ExecutorService inputExecutor = ThreadPoolTool.getInstance().getExecutor("input", 4, 100);

    public void run() {
        if (null != loginRequest) {
            //do login
            input.input(loginRequest);
        }


        while (true) {
            Message message = MessageQueue.take();
            if (message.getType() == MessageEnum.REQUEST) {
                Request request = (Request) message.getData();
                Future<Page> pageFuture = inputExecutor.submit(() -> input.input(pageRequest));

            }
        }

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

        List<Request> requests = Arrays.asList(loginRequest, pageRequest);
        Adult adult = new Adult.Builder().login(loginRequest).page(pageRequest).build();

    }


}
