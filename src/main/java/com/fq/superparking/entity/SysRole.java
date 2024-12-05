package com.fq.superparking.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统角色
 * @TableName sys_role
 */
@Data
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 所属机构
     */
    private Long orgId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleSign;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建用户id
     */
    private Long userIdCreate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建时间
     */
    private Date gmtModified;


    private static final long serialVersionUID = 1L;
}