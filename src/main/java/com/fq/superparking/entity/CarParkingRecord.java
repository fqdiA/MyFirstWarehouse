package com.fq.superparking.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName app_car_parking_record
 */
@Data
public class CarParkingRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private BigDecimal cost;

    /**
     * 
     */
    private Date gmtInto;

    /**
     * 
     */
    private Date gmtOut;

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
    private Long parkManageId;

    /**
     * 
     */
    private String parkManageName;

    /**
     * 
     */
    private String plateNumber;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private String orderNo;

    private static final long serialVersionUID = 1L;
}