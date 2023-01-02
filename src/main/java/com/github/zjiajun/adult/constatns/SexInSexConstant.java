package com.github.zjiajun.adult.constatns;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 23:55
 */
public interface SexInSexConstant {

    String HOST = "www.sexinsex.net";
    String BASE_URL = "http://" + HOST + "/bbs/";

    String LOGIN_URL = BASE_URL + "logging.php?action=login";

    String FORUM_URL = "forum-%d-1.html";

    String YOU_MA_FORUM_URL = BASE_URL + String.format(FORUM_URL, 230);

    String WU_MA_FORUM_URL = BASE_URL + String.format(FORUM_URL, 143);


}
