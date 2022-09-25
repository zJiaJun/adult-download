package com.github.zjiajun.adult.constatns;

import com.github.zjiajun.adult.config.AppConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/9/16 23:23
 */
public enum RequestType {

    LOGIN {
        @Override
        public Map<String, String> data(String identify) {
            Map<String, String> param = new HashMap<>();
            param.put("referer", "index.php");
            param.put("loginfield", "username");
            param.put("username", AppConfig.userName());
            param.put("password", AppConfig.password());
            param.put("questionid", "0");
            param.put("answer", "");
            param.put("cookietime", "315360000");
            param.put("loginmode", "");
            param.put("styleid", "");
            param.put("loginsubmit", "true");
            return param;
        }
    },
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
