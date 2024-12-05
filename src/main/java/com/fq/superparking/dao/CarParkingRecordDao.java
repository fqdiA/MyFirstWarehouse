package com.fq.superparking.dao;

import com.fq.superparking.entity.CarParkingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author fang
* @description 针对表【app_car_parking_record】的数据库操作Mapper
* @createDate 2023-10-28 15:38:04
* @Entity com.fq.superparking.entity.CarParkingRecord
*/
@Mapper
public interface CarParkingRecordDao {

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
    int insert(CarParkingRecord record);

    /**
     * 插入选择性
     *
     * @param record 记录
     * @return int
     */
    int insertSelective(CarParkingRecord record);

    /**
     * 按主键选择
     *
     * @param id id
     * @return {@link CarParkingRecord}
     */
    CarParkingRecord selectByPrimaryKey(Long id);

    /**
     * 按主键选择更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(CarParkingRecord record);

    /**
     * 按主键更新
     *
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKey(CarParkingRecord record);

    /**
     * 查询所有
     *
     * @param plateNumber  车牌号
     * @param orgId        组织id
     * @param parkManageId 停车场id
     * @param IntoTime     入场时间
     * @param OutTime      出场时间
     * @return {@link List}<{@link CarParkingRecord}>
     */
    List<CarParkingRecord> queryAll(@Param("plateNumber") String plateNumber, @Param("orgId") Long orgId, @Param("parkManageId") Long parkManageId,@Param("IntoTime") Date IntoTime,@Param("OutTime") Date OutTime);

    /**
     * 按车牌号码查询
     *
     * @param plateNumber 车牌号码
     * @return {@link List}<{@link CarParkingRecord}>
     */
    List<CarParkingRecord> queryByPlateNumber(String plateNumber);
}
