package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarParkingRecord;
import com.fq.superparking.entity.vo.CarManageVo;
import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * 停车记录服务
 *
 * @author fang
 * @date 2023/10/29
 */
public interface CarRecordService {
    /**
     * 找到所有
     * 分页查询
     *
     * @param pageNum      当前页
     * @param pageSize     页大小
     * @param plateNumber  车牌号
     * @param orgId        合作单位id
     * @param parkManageId 停车场id
     * @param IntoTime     入场时间
     * @param OutTime      出场时间
     * @return {@link R}<{@link PageInfo}<{@link CarParkingRecord}>>
     */
    R<PageInfo<CarParkingRecord>> findAll(Integer pageNum, Integer pageSize, String plateNumber, Long orgId, Long parkManageId,Date IntoTime,Date OutTime);


    /**
     * 插入
     * 新增停车记录
     *
     * @param number 车牌号
     * @param parkId 停车场id
     * @param numberPlateUrl 车牌图片路径
     * @return {@link R}<{@link String}>
     */
    R<String> insert(String numberPlateUrl,String number,Long parkId);


}
