package com.muyu.newhire.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RateUserDto {
    @NotNull
    private Integer level;
}
