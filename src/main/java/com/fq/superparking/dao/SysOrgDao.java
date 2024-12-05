package com.fq.superparking.dao;

import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.dto.OrgDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 机构管理(SysOrg)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-19 10:38:49
 */
@Mapper
public interface SysOrgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orgId 主键
     * @return 实例对象
     */
    SysOrg queryById(Long orgId);

    /**
     * 查询指定行数据
     *
     * @param sysOrg   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysOrg> queryAllByLimit(SysOrg sysOrg, @Param("pageable") Pageable pageable);


    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<SysOrg> queryAll();

    /**
     * 查询所有数据 分页查询
     *
     * @return 对象列表
     */
    List<SysOrg> queryAllByName(String name);

    /**
     * 统计总行数
     *
     * @param sysOrg 查询条件
     * @return 总行数
     */
    long count(SysOrg sysOrg);

    /**
     * 新增数据
     *
     * @param sysOrg 实例对象
     * @return 影响行数
     */
    int insert(SysOrg sysOrg);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysOrg> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysOrg> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysOrg> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysOrg> entities);

    /**
     * 修改数据
     *
     * @param sysOrg 实例对象
     * @return 影响行数
     */
    int update(SysOrg sysOrg);

    /**
     * 通过主键删除数据
     *
     * @param orgId 主键
     * @return 影响行数
     */
    int deleteById(Long orgId);

    /**
     * 查询组织单位 全称
     *
     * @return {@link List}<{@link OrgDTO}>
     */
    List<OrgDTO> getList();

}

