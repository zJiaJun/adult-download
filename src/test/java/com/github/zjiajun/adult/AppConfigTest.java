package com.github.zjiajun.adult;

import com.github.zjiajun.adult.tool.AppConfig;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by zhujiajun
 * 16/6/25 22:09
 */
public class AppConfigTest {

    @Test
    public void shouldEqualsPicDownloadPath() {
        assertEquals(AppConfig.getPicDownloadPath(),"/Users/zhujiajun/Downloads/pic/rosi/");
    }

    @Test
    public void shouldReadUaList() {
        assertEquals(AppConfig.getUaList().size(),13);
    }



}
