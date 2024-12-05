package com.fq.superparking.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统日志
 * @TableName sys_log
 */
@Data
public class SysLog implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 响应时间
     */
    private Integer time;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 访问方式 0:PC 1:手机 2:未知
     */
    private Integer deviceType;

    /**
     * 类型 0: 一般日志记录 1: 异常错误日志
     */
    private Integer logType;

    /**
     * 异常详细信息
     */
    private String exceptionDetail;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    private static final long serialVersionUID = 1L;
}