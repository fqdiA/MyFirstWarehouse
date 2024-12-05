package com.fq.superparking.dao;

import com.fq.superparking.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author fang
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2023-11-18 21:29:53
* @Entity com.fq.superparking.entity.SysLog
*/
@Mapper
public interface SysLogDao {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    /**
     * 按用户名模糊查询
     *
     * @param username 用户名
     * @return {@link List}<{@link SysLog}>
     */
    List<SysLog> queryAllByUsername(@Param("username") String username);
}
