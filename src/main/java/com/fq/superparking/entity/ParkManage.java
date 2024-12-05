package com.fq.superparking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车场
 *
 * @TableName app_car_park_manage
 */
@Data
public class ParkManage implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     *
     */
    private Integer freeTime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /**
     * 
     */
    private BigDecimal maxMoney;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Long orgId;

    /**
     * 
     */
    private String orgName;

    /**
     * 
     */
    private Integer parkingSpaceNumber;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer timeUnit;

    /**
     * 
     */
    private BigDecimal unitCost;

    /**
     * 
     */
    private Long userIdCreate;

    private static final long serialVersionUID = 1L;
}