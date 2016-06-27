package com.github.zjiajun.adult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by zhujiajun
 * 15/5/15 15:565
 */
@Deprecated
public class DownloadUtils {

    public static void download(InputStream inputStream,String downloadPath,String fileName) {

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(downloadPath + fileName);
            byte[] buffer = new byte[1204];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fs.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
