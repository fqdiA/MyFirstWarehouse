package com.fq.superparking.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName app_order
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String body;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private String orderNo;

    /**
     * 
     */
    private Long orgId;

    /**
     * 
     */
    private Long parkManageId;

    /**
     * 
     */
    private String plateNumber;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private BigDecimal totalFee;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Long userCreate;

    /**
     * 
     */
    private Date validityTime;

    /**
     * 字符串的类型
     */
    private String typeString;

    private static final long serialVersionUID = 1L;
}