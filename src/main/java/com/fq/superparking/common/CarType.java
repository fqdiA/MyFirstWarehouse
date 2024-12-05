package com.fq.superparking.common;

/**
 * 汽车类型枚举类
 *
 * @author fang
 * @date 2023/10/28
 */
public enum CarType {
    MONTH_CAR(0,"包月车"),
    VIP_CAR(1,"VIP车"),
    TEMP_CAR(2,"临时车"),
    ;
    private final Integer code;
    private final String value;

    private CarType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
