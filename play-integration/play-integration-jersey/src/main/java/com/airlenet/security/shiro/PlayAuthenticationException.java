package com.airlenet.security.shiro;

/**
 * @author airlenet
 * @version 2017-10-04
 */
public class PlayAuthenticationException extends RuntimeException {
    public PlayAuthenticationException() {
    }

    public PlayAuthenticationException(String message) {
        super(message);
    }

    public PlayAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayAuthenticationException(Throwable cause) {
        super(cause);
    }

    public PlayAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}