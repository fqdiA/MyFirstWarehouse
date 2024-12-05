package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.dto.OrgDTO;
import com.fq.superparking.entity.vo.SysOrgVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 组织服务层接口
 *
 * @author fang
 * @version 1.0.0
 * @date 2023/10/21
 */
public interface OrgService {

    /**
     * 分页查询
     *
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @param name 查询的单位名称
     * @return {@link R}<{@link PageInfo}<{@link List}<{@link SysOrg}>>>
     */
    R<PageInfo<SysOrg>> findAll(Integer pageNum,Integer pageSize,String name);


    /**
     * 添加
     *
     * @param sysOrg
     * @return {@link R}
     */
    R<String> insert(SysOrgVo sysOrg);

    /**
     * 修改
     *
     * @param vo
     * @return {@link R}
     */
    R<String> update(SysOrgVo vo);

    /**
     * 删除
     *
     * @param id
     * @return {@link R}
     */
    R<String> deleteById(Long id);

    /**
     * 查询信息然后编辑
     *
     * @param id
     */
    R<SysOrg> findById(Integer id);


    /**
     * 查询组织单位 全称
     *
     * @return {@link List}<{@link OrgDTO}>
     */
    R<List<OrgDTO>> list();

    /**
     * 查询统计
     *
     * @return {@link Long}
     */
    Long queryCount();
}
