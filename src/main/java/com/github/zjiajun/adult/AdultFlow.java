package com.github.zjiajun.adult;

import com.github.zjiajun.adult.common.Message;
import com.github.zjiajun.adult.common.MessageQueue;
import com.github.zjiajun.adult.common.MessageType;
import com.github.zjiajun.adult.input.Input;
import com.github.zjiajun.adult.input.InputService;
import com.github.zjiajun.adult.process.ListPageProcess;
import com.github.zjiajun.adult.process.Process;
import com.github.zjiajun.adult.tool.AdultConfig;
import com.github.zjiajun.adult.tool.ThreadPoolTool;

import java.util.concurrent.ExecutorService;

/**
 * @author zhujiajun
 * @since 2017/2/16
 */
public class AdultFlow {


    private final ExecutorService executor = ThreadPoolTool.getInstance().getExecutor("test", 4, 100);

    public void flow(AdultConfig adultConfig) {
        //main thread
        while (true) {
            Message message = MessageQueue.take();
            if (MessageType.PAGE_LIST == (message.getType())) {
                Object data = message.getData();
                Input input = new InputService();
                Page page = input.input((Request) data);
                Process process = new ListPageProcess();
                PageResult pageResult = process.process(page);


            }
        }

    }
}
