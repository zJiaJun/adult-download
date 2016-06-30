package com.github.zjiajun.adult;

import com.github.zjiajun.adult.pic.PicApp;
import com.github.zjiajun.adult.torrent.TorrentApp;

/**
 * Created by zhujiajun
 * 16/6/30 15:15
 *
 * 运行主类
 */
public class App {

    public static void main(String[] args) {
        if (args.length == 0) return;
        switch (args[0]) {
            case "p" :
                new PicApp().handlerRosixz();
                break;
            case "t" :
                new TorrentApp().handlerSexInSex();
                break;
            default:
                System.err.println("参数错误!!!");
        }

    }
}
