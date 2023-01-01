package com.github.zjiajun.adult.context;

import com.github.zjiajun.adult.model.SiteData;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2023/1/1 19:17
 */
public class AdultContext {

    private static final ThreadLocal<SiteData> SITE_DATA_LOCAL = new ThreadLocal<>();

    public static void setSiteData(SiteData siteData) {
        SITE_DATA_LOCAL.set(siteData);
    }

    public static SiteData getSiteData() {
        return SITE_DATA_LOCAL.get();
    }

    public static void clear() {
        SITE_DATA_LOCAL.remove();
    }


}
