package com.muyu.newhire.exception;

public abstract class BaseException extends RuntimeException {

    public abstract int getStatus();

    public abstract String getCode();

    protected BaseException(String message) {
        super(message);
    }
}
