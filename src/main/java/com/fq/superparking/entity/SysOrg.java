package com.fq.superparking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 机构管理(SysOrg)实体类
 *
 * @author makejava
 * @since 2023-10-19 09:57:00
 */
@Data
public class SysOrg implements Serializable {
    private static final long serialVersionUID = 206567200070385017L;
    /**
     * 机构id
     */
    private Long orgId;
    /**
     * 上级机构ID，一级机构为0
     */
    private Long parentId;
    /**
     * 机构编码
     */
    private String code;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构名称(全称)
     */
    private String fullName;
    /**
     * 机构负责人
     */
    private String director;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 可用标识  1：可用  0：不可用
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date gmtModified;


}

