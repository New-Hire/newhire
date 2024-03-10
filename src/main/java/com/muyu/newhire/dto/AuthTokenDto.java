package com.muyu.newhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthTokenDto {
    private String account;
    private String password;
}
