package com.muyu.newhire.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private int status;
    private String code;
    private String message;
}
