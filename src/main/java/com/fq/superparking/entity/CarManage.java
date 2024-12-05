package com.fq.superparking.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 停车场管理
 *
 * @TableName app_car_manage
 */
@Data
public class CarManage implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String gender;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String nickname;

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
    private String parkingLot;

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
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 
     */
    private Long userIdCreate;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date validityTime;

    /**
     * 
     */
    private String mobile;

    private static final long serialVersionUID = 1L;
}