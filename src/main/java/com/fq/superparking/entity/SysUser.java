package com.fq.superparking.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户
 * @TableName sys_user
 */
@Data
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 所属机构
     */
    private Long orgId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名(昵称)
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;

    /**
     * 头像上传 0:未上传 1:上传
     */
    private Integer avatarStatus;

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
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 是否修改过初始密码
     */
    private Integer isModifyPwd;

    /**
     * 令牌
     */
    private String token;

    /**
     * 角色id
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}