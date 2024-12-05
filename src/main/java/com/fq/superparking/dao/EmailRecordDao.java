package com.fq.superparking.dao;

import com.fq.superparking.entity.EmailRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author fang
* @description 针对表【app_email_record】的数据库操作Mapper
* @createDate 2023-11-29 18:52:53
* @Entity com.fq.superparking.entity.EmailRecord
*/
@Mapper
public interface EmailRecordDao {

    int deleteByPrimaryKey(Long id);

    int insert(EmailRecord record);

    int insertSelective(EmailRecord record);

    EmailRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmailRecord record);

    int updateByPrimaryKey(EmailRecord record);

    /**
     * 查询所有页面
     *
     * @param emailMotif 邮件主题
     * @return {@link List}<{@link EmailRecord}>
     */
    List<EmailRecord> queryAllPage(@Param("emailMotif") String emailMotif);



}
