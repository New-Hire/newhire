package com.muyu.newhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAuthTokenDto {
    private long userId;
    private String role;
    private String token;
}
