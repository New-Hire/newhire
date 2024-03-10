package com.muyu.newhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAuthUserDto {
    private String account;
    private String type;
}
