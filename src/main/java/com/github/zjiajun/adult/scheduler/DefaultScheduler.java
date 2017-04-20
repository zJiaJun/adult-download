package com.github.zjiajun.adult.scheduler;

import com.github.zjiajun.adult.request.Request;
import com.github.zjiajun.adult.response.Response;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhujiajun
 * @since 2017/4/5
 */
public class DefaultScheduler implements Scheduler {

    private final BlockingQueue<Request> requestBlockingQueue;
    private final BlockingQueue<Response> responseBlockingQueue;

    public DefaultScheduler() {
        this.requestBlockingQueue = new LinkedBlockingQueue<>();
        this.responseBlockingQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void putRequest(Request request) {
        try {
            requestBlockingQueue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Request takeRequest() {
        Request request = null;
        try {
            request = requestBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public void putResponse(Response response) {
        try {
            responseBlockingQueue.put(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Response takeResponse() {
        Response response = null;
        try {
            response = responseBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}
