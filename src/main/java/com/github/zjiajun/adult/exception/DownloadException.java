package com.github.zjiajun.adult.exception;

/**
 * @author zhujiajun
 * @since 2017/1/30
 */
public class DownloadException extends AdultException {

    private static final long serialVersionUID = -331963622959458724L;

    public DownloadException(String message) {
        super(message);
    }

    public DownloadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadException(Throwable cause) {
        super(cause);
    }

    public DownloadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
