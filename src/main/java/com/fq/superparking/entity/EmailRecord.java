package com.fq.superparking.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName app_email_record
 */
@Data
public class EmailRecord implements Serializable {
    /**
     * 邮件记录id
     */
    private Long id;

    /**
     * 发送人id
     */
    private Long sendUserId;

    /**
     * 发送人昵称
     */
    private String sendUserUsername;

    /**
     * 发送人的邮箱
     */
    private String sendUserEmail;

    /**
     * 收件人id
     */
    private Long addresseeUserId;


    /**
     * 收件人昵称
     */
    private String addresseeUserUsername;

    /**
     * 收件人邮箱
     */
    private String addresseeUserEmail;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 邮件主题
     */
    private String emailMotif;

    /**
     * 邮件正文
     */
    private String emailSubject;

    private static final long serialVersionUID = 1L;

}