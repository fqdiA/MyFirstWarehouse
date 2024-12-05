package com.fq.superparking.dao;

import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.entity.vo.ParkManageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author fang
* @description 针对表【app_car_park_manage】的数据库操作Mapper
* @createDate 2023-10-23 14:59:44
* @Entity com.fq.superparking.entity.AppCarParkManage
*/
@Mapper
public interface ParkManageDao {

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
    int insert(ParkManage record);

    /**
     * 插入选择性
     *
     * @param record 记录
     * @return int
     */
    int insertSelective(ParkManage record);

    /**
     * 按主键选择
     *
     * @param id id
     * @return {@link ParkManage}
     */
    ParkManage selectByPrimaryKey(Long id);

    /**
     * 按主键选择更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(ParkManage record);

    /**
     * 按主键更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(ParkManage record);

    /**
     * 查询所有
     *
     * @param name 名字
     * @param orgId 停车场单位id
     * @return {@link List}<{@link ParkManage}>
     */
    List<ParkManage> queryAll(@Param("name") String name,@Param("orgId") Integer orgId);

    /**
     * 列表id和名称
     *
     * @return {@link List}<{@link ParkManageVo}>
     */
    @Select("select id,name from app_car_park_manage")
    List<ParkManageVo> listIdAndName();

    /**
     * 查找记录数
     *
     * @return {@link Integer}
     */
    Integer getCount();

    /**
     * 多选删除
     *
     * @param deletes 删除
     * @return {@link Integer}
     */
    Integer deleteS(String deletes);
}
