package com.fq.superparking.entity.vo;

import lombok.Data;

@Data
public class SysUserVo {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
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
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 机构id
     */
    private Long orgId;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id
     */
    private Long roleId;
}
