package com.github.zjiajun.adult;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author zhujiajun
 * @since 2017/1/31
 */
public class RequestTest {


    @Test
    public void shouldBuildRequestSuccess() {
        Request request = new Request.Builder().url("http://www.google.com").get().build();
        assertEquals("http://www.google.com", request.getUrl());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPE() {
        new Request.Builder().build();
    }

}
