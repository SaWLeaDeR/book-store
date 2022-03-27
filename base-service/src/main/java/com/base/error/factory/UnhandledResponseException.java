package com.base.error.factory;

public class UnhandledResponseException extends RuntimeException {

    private static final long serialVersionUID = -2056031270279769676L;

    public UnhandledResponseException(String message) {
        super(message);
    }
}
