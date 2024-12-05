package com.fq.superparking.dao;

import com.fq.superparking.entity.SysRole;
import com.fq.superparking.entity.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author fang
* @description 针对表【sys_role(系统角色)】的数据库操作Mapper
* @createDate 2023-11-11 12:11:01
* @Entity com.fq.superparking.entity.SysRole
*/
@Mapper
public interface SysRoleDao {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 按角色标识查询
     *
     * @param sign 角色标识
     * @return {@link List}<{@link SysRole}>
     */
    List<SysRole> queryAllBySign(String sign);


    /**
     * 查询id和名称
     *
     * @return {@link List}<{@link SysRoleVo}>
     */
    List<SysRole> queryIdAndName();

}
