package com.github.zjiajun.adult;

import java.util.Random;

/**
 * Created by zhujiajun
 * 15/5/17 20:29
 */
public class UserAgentUtils {

    public static String randomUserAgent() {
        String [] userAgents = {BrowserUserAgent.MAC,BrowserUserAgent.CHROME_UBUNTU_64,BrowserUserAgent.CHROME_WIN7_64,
                BrowserUserAgent.FIREFOX_WIN7_6x,BrowserUserAgent.FIREFOX_UBUNTU_64,
                BrowserUserAgent.QQ_WIN7_64,BrowserUserAgent.QQ_WINXP_32,
                BrowserUserAgent.S360_WIN7_64,BrowserUserAgent.S360_WINXP_32,
                BrowserUserAgent.TAOBAO_WIN7_64,BrowserUserAgent.LIEBAO_WIN7_64,
                BrowserUserAgent.SOUGOU_WINXP_32,BrowserUserAgent.IE_WIN7_64};
        return userAgents[new Random().nextInt(userAgents.length)];
    }
}
