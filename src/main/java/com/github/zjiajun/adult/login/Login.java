package com.github.zjiajun.adult.login;

import com.github.zjiajun.adult.SiteContext;

/**
 * @author zhujiajun
 * @since 2017/1/29
 */
public interface Login {

    /**
     * 登录网站
     * @param context 网站上下文
     */
    void login(SiteContext context);

}
