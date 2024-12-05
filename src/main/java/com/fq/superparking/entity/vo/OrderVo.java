package com.fq.superparking.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVo {
    /**
     * 订单id
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 停车费用
     */
    private BigDecimal totalFee;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 支付类型
     */
    private String type;
    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 备注
     */
    private String remark;
}
