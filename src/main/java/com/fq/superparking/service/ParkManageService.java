package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.entity.vo.ParkManageVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 停车场管理服务层接口
 *
 * @author fang
 * @version 1.0.0
 * @date 2023/10/23
 */
public interface ParkManageService {
    /**
     * 分页查询 以及根据停车场名字模糊查询
     *
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @param name 停车场名字
     * @param orgId 单位id
     * @return {@link R}<{@link PageInfo}<{@link ParkManage}>>
     */
    R<PageInfo<ParkManage>> findByPage(Integer pageNum,Integer pageSize,String name,Integer orgId);

    /**
     * 添加停车场
     *
     * @param parkManage
     * @return {@link R}<{@link String}>
     */
    R<String> insert(ParkManage parkManage);

    /**
     * 更新停车场
     *
     * @param parkManage
     * @return {@link R}<{@link String}>
     */
    R<String> update(ParkManage parkManage);

    /**
     * 删除停车场
     *
     * @param id 停车场id
     * @return {@link R}<{@link String}>
     */
    R<String> deleteById(Long id);

    /**
     * 根据id查询停车场信息
     *
     * @param id 停车场id
     * @return {@link R}<{@link ParkManage}>
     */
    R<ParkManage> findById(Long id);

    /**
     * 停车场列表id和名称
     *
     * @return {@link R}<{@link List}<{@link ParkManageVo}>>
     */
    R<List<ParkManageVo>> listIdAndName();

    /**
     * 得到停车场记录数
     *
     * @return {@link Integer}
     */
    Integer getCount();

    /**
     * 多选删除
     *
     * @param deletes 删除
     * @return {@link R}<{@link Integer}>
     */
    R<Integer> deleteS(String deletes);
}
