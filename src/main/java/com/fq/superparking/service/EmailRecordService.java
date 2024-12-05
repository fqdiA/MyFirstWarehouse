package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.EmailRecord;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmailRecordService {
    /**
     * 查询所有页面
     *
     * @param emailMotif 邮件主题
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @return {@link PageInfo}<{@link EmailRecord}>
     */
    R<PageInfo<EmailRecord>> queryAllPage(Integer pageNum, Integer pageSize, String emailMotif);

    /**
     * 添加邮件记录信息
     *
     * @param emailRecord 邮件记录
     * @return {@link R}<{@link String}>
     */
    R<String> addEmailRecordInfo(EmailRecord emailRecord);

    /**
     * 删除邮件记录信息
     *
     * @param id id
     * @return {@link R}<{@link String}>
     */
    R<String> deleteEmailRecordInfo(Long id);
}
