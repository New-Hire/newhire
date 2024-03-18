package com.muyu.newhire.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RateCalcStatus {
    // 正在计算中
    READY(0),
    // 计算完成
    END(1),
    // 计算失败
    FAILED(2);
    @JsonValue
    private final int value;

    RateCalcStatus(int value) {
        this.value = value;
    }
}
