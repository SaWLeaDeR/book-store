package com.base.exception;

public class PropertyLoadingException extends RuntimeException {
    private static final long serialVersionUID = 5557298722190256734L;

    public PropertyLoadingException(String message) {
        super(message);
    }

    public PropertyLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
