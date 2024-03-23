package com.muyu.newhire.constant;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    NOT_FOUND_COMPANY("企业未找到"),
    BAD_REQUEST("请求参数不合法");

    ExceptionCode(String message) {
        this.message = message;
    }

    private final String message;
}
