package com.fq.superparking.dao;

import com.fq.superparking.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author fang
* @description 针对表【sys_user(系统用户)】的数据库操作Mapper
* @createDate 2023-11-18 19:07:16
* @Entity com.fq.superparking.entity.SysUser
*/
@Mapper
public interface SysUserDao {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /*@Select("select * from sys_user where username = #{username} limit 1")*/
    SysUser queryByUsername(@Param("username") String username);

    /**
     * 按用户名查询用户信息
     *
     * @param username 用户名
     * @return {@link List}<{@link SysUser}>
     */
    List<SysUser> selectInfoByUserName(@Param("username") String username,@Param("orgId") Integer orgId);

    /**
     * 选择用户id和用户名
     *
     * @return {@link List}<{@link SysUser}>
     */
    List<SysUser> selectUserIdAndUsername();
}
