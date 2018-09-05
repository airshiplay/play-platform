package com.airlenet.data.domain;

public class LockedableException extends RuntimeException {
    public LockedableException() {
    }

    public LockedableException(String message) {
        super(message);
    }

    public LockedableException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockedableException(Throwable cause) {
        super(cause);
    }

    public LockedableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
