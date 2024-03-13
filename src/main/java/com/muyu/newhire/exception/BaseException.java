package com.muyu.newhire.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public abstract int getStatus();

    public abstract String getCode();

    protected BaseException(String message) {
        super(message);
    }
}
