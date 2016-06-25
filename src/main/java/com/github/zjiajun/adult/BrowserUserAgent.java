package com.github.zjiajun.adult;

/**
 * Created by zhujiajun
 * 15/5/17 20:03
 */
@Deprecated
public interface BrowserUserAgent {


    /**
     *  MAC 本机
     */
    String MAC = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";

    /**
     *  淘宝浏览器2.0 on Windows 7 x64
     */
    String TAOBAO_WIN7_64  = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11";

    /**
     *  猎豹浏览器1.5.9.2888 急速模式on Windows 7 x64：
     */
    String LIEBAO_WIN7_64 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 LBBROWSER";

    /**
     *  QQ浏览器7.0 on Windows 7 x64 IE9
     */
    String QQ_WIN7_64 = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)";

    /**
     *  QQ浏览器7.0 on Windows XP x86 IE6
     */
    String QQ_WINXP_32 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)";

    /**
     *  360安全浏览器5.0自带IE8内核版 on Windows XP x86 IE6
     */
    String S360_WINXP_32 = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; 360SE)";

    /**
     *  360安全浏览器5.0 on Windows 7 x64 IE9
     */
    String S360_WIN7_64 = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";

    /**
     *  搜狗浏览器4.0 高速模式 on Windows XP x86：
     */
    String SOUGOU_WINXP_32 = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0";

    /**
     *  Firefox x64 4.0b13pre on Windows 7 x64
     */
    String FIREFOX_WIN7_6x = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b13pre) Gecko/20110307 Firefox/4.0b13pre";

    /**
     *  Firefox x64 on Ubuntu 12.04.1 x64
     */
    String FIREFOX_UBUNTU_64 = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0";

    /**
     *  Chrome x64 on Ubuntu 12.04.1 x64
     */
    String CHROME_UBUNTU_64 = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11";

    /**
     *  Chrome x86 23.0.1271.64 on Windows 7 x64
     */
    String CHROME_WIN7_64 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11";

    /**
     *  IE9 x64 9.0.8112.16421 on Windows 7 x64：
     */
    String IE_WIN7_64 = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";

    /**
     *  andorid 2.2自带浏览器，不支持HTML5视频
     */
    String ANDROID = "Mozilla/5.0 (Linux; U; Android 2.2.1; zh-cn; HTC_Wildfire_A3333 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";

    /**
     *  Ipad
     */
    String IPAD = "Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5";

}
