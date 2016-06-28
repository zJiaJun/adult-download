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

    @Test
    public void shouldEqualsSexInSexHost() {
        assertEquals(AdultConfig.sexInSexHost(),"67.220.90.4");
    }

    @Test
    public void shouldEqualsSexInSexUrlPrefix() {
        assertEquals(AdultConfig.sexInSexUrlPrefix(),"http://67.220.90.4/forum/");
    }

    @Test
    public void shouldEqualsSexInSexLoginUrl() {
        assertEquals(AdultConfig.sexInSexLoginUrl(),"http://67.220.90.4/forum/logging.php?action=login");
    }

    @Test
    public void shouldEqualsSexInSexAsiaWuUrl() {
        assertEquals(AdultConfig.sexInSexAsiaWuUrl(),"http://67.220.90.4/forum/forum-143-1.html");
    }

    @Test
    public void shouldEqualsSexInSexAsiaYouUrl() {
        assertEquals(AdultConfig.sexInSexAsiaYouUrl(),"http://67.220.90.4/forum/forum-230-1.html");
    }

}
