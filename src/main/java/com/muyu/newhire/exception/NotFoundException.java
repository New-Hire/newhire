package com.muyu.newhire.exception;

import com.muyu.newhire.constant.ExceptionCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    private final ExceptionCode exceptionCode;

    public NotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    @Override
    public int getStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getCode() {
        return exceptionCode.name();
    }

}
