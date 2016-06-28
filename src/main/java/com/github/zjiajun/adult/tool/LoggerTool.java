package com.github.zjiajun.adult.tool;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zhujiajun
 * 16/6/27 20:20
 *
 * 日志工具类
 */
public class LoggerTool {

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
