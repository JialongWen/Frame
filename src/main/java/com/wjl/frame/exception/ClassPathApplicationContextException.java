package com.wjl.frame.exception;

public class ClassPathApplicationContextException extends Exception {
    public ClassPathApplicationContextException() {
        super();
    }

    public ClassPathApplicationContextException(String message) {
        super(message);
    }

    public ClassPathApplicationContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassPathApplicationContextException(Throwable cause) {
        super(cause);
    }

    protected ClassPathApplicationContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
