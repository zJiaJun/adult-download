package com.github.zjiajun.adult.exception;

/**
 * Created by zhujiajun
 * 16/6/26 12:39
 */
public class AdultException extends RuntimeException {

    private static final long serialVersionUID = 6715728062403374834L;

    public AdultException() {
        super();
    }

    public AdultException(String message) {
        super(message);
    }

    public AdultException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdultException(Throwable cause) {
        super(cause);
    }

    protected AdultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
