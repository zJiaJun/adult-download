package com.github.zjiajun.adult;

import com.github.zjiajun.adult.input.Input;
import com.github.zjiajun.adult.input.InputService;
import com.github.zjiajun.adult.tool.AdultConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiajun
 * @since 2017/2/16
 */
public class AdultFlow {


    public void flow(AdultConfig adultConfig) {
        //流程处理
        List<Request> requestList = new ArrayList<>();
        boolean needLogin = true;
        if (needLogin) {
            Request loginRequest = requestList.get(0);

        }
        Request request = new Request.Builder().build();
        Input input = new InputService();
        input.input(request);
    }
}
