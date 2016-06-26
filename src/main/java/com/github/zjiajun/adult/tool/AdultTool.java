package com.github.zjiajun.adult.tool;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.github.zjiajun.adult.tool.AdultConfig.*;

/**
 * Created by zhujiajun
 * 16/6/26 12:25
 *
 * 工具类
 */
public final class AdultTool {


    public static String randomUa() {
        List<String> uaList = uaList();
        Collections.shuffle(uaList);
        return uaList().get(new Random().nextInt(uaList.size()));
    }

}
