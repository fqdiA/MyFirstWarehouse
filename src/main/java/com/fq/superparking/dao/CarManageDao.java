package com.fq.superparking.dao;

import com.fq.superparking.entity.CarManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author fang
* @description 针对表【app_car_manage】的数据库操作Mapper
* @createDate 2023-10-28 15:35:52
* @Entity com.fq.superparking.entity.CarManage
*/
@Mapper
public interface CarManageDao {

    /**
     * 按主键删除
     *
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     *
     * @param record 记录
     * @return int
     */
    int insert(CarManage record);

    /**
     * 插入选择性
     *
     * @param record 记录
     * @return int
     */
    int insertSelective(CarManage record);

    /**
     * 按主键选择
     *
     * @param id id
     * @return {@link CarManage}
     */
    CarManage selectByPrimaryKey(Long id);

    /**
     * 按主键选择更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(CarManage record);

    /**
     * 按主键更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(CarManage record);

    /**
     * 找到所有
     *
     * @param name 车牌号或者车主姓名
     * @param orgId 组织单位id
     * @return {@link List}<{@link CarManage}>
     */
    List<CarManage> findAll(@Param("name") String name, @Param("orgId") Integer orgId);

    /**
     * 按车牌号查询车辆类型
     *
     * @param plateNumber 塔板数
     * @return {@link CarManage}
     */
    CarManage selectAllByPlateNumber(String plateNumber);

    /**
     * 查找记录数
     *
     * @return {@link Integer}
     */
    Integer getCount();
}
