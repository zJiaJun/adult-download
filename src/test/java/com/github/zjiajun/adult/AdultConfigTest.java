package com.github.zjiajun.adult;

import com.github.zjiajun.adult.tool.AdultConfig;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by zhujiajun
 * 16/6/25 22:09
 */
public class AdultConfigTest {

    @Test
    public void shouldEqualsPicDownloadPath() {
        assertEquals(AdultConfig.picDownloadPath(),"/Users/zhujiajun/Downloads/pic/");
    }

    @Test
    public void shouldReadUaList() {
        assertEquals(AdultConfig.uaList().size(),13);
    }

    @Test
    public void shouldEqualsUrl() {
        assertEquals(AdultConfig.rosixzUrl(),"http://www.rosixz.com/");
    }


}
