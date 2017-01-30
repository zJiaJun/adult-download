package com.github.zjiajun.adult.exception;

/**
 * @author zhujiajun
 * @since 2017/1/30
 */
public class LoginException extends AdultException {

    private static final long serialVersionUID = 2198148031196578187L;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
