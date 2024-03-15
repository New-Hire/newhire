package com.muyu.newhire.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryUserDto {
    @Nullable
    private Long currentCompanyId;
}
