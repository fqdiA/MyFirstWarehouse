package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarManage;
import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.dto.OrgDTO;
import com.fq.superparking.entity.vo.CarManageVo;
import com.fq.superparking.entity.vo.SysOrgVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 车辆管理服务
 *
 * @author fang
 * @date 2023/10/28
 */
public interface CarManagerService {
    /**
     * 找到所有
     * 分页查询
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @param name     车牌号或者车主姓名
     * @param orgId    组织单位id
     * @return {@link R}<{@link PageInfo}<{@link CarManage}>>
     */
    R<PageInfo<CarManage>> findAll(Integer pageNum, Integer pageSize, String name, Integer orgId);


    /**
     * 插入
     *
     * @param car 车辆信息
     * @return {@link R}<{@link String}>
     */
    R<String> insert(CarManage car);

    /**
     * 修改
     *
     * @param car 车辆
     * @return {@link R}
     */
    R<String> update(CarManage car);

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
     * @param id 车辆id
     */
    R<CarManage> findById(Integer id);

    /**
     * 查找记录数
     *
     * @return {@link Integer}
     */
    Integer getCount();
}
