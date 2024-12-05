package com.fq.superparking.common;

import io.swagger.models.auth.In;

/**
 * 订单类型枚举类
 *
 * @author fang
 * @date 2023/11/13
 */
public enum OrderType {
    WEIXIN_ORDER(0,"微信"),
    ZHIFUBAO_ORDER(1,"支付宝"),
    PINGGUO_ORDER(2,"苹果"),
    HUAWEI_ORDER(3,"华为")
    ;
    private final Integer code;
    private final String value;

    OrderType(Integer code, String value) {
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
