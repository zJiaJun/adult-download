package com.github.zjiajun.adult.constatns;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 23:23
 */
public enum RequestType {

    WEB {
        @Override
        public Map<String, String> data(String empty) {
            return null;
        }
    },
    DOWNLOAD {
        @Override
        public Map<String, String> data(String fileName) {
            Map<String, String> data = new HashMap<>();
            data.put(SexInSexConstant.FILE_NAME_KEY, fileName);
            return data;
        }
    };

    public abstract Map<String, String> data(String identify);
}
