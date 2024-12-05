package com.fq.superparking.service.impl;


import com.fq.superparking.common.CarType;
import com.fq.superparking.common.R;
import com.fq.superparking.dao.CarManageDao;
import com.fq.superparking.dao.CarParkingRecordDao;
import com.fq.superparking.dao.ParkManageDao;
import com.fq.superparking.entity.CarManage;
import com.fq.superparking.entity.CarParkingRecord;
import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.service.CarRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CarRecordServiceImpl implements CarRecordService {

    // 停车记录
    final CarParkingRecordDao recordDao;

    // 停车场
    final ParkManageDao parkManageDao;

    // 车辆
    final CarManageDao carManageDao;

    @Override
    public R<PageInfo<CarParkingRecord>> findAll(Integer pageNum, Integer pageSize, String plateNumber, Long orgId, Long parkManageId,Date IntoTime,Date OutTime) {
        PageHelper.startPage(pageNum,pageSize);
        List<CarParkingRecord> list = recordDao.queryAll(plateNumber, orgId, parkManageId,IntoTime,OutTime);
        PageInfo<CarParkingRecord> pageInfo = new PageInfo<>(list);
        return R.ok(pageInfo);
    }

    @Override
    public R insert(String numberPlateUrl,String number, Long parkId) {
        log.info("车辆{}进入停车场id{}",number,parkId);

        // 查出停车场信息
        ParkManage parkManage = parkManageDao.selectByPrimaryKey(parkId);

        // 停车场记录实体类
        CarParkingRecord record = new CarParkingRecord();
        // 单位id名称
        record.setOrgId(parkManage.getOrgId());
        record.setOrgName(parkManage.getOrgName());
        // 停车场id名称
        record.setParkManageId(parkManage.getId());
        record.setParkManageName(parkManage.getName());
        // 设置车牌号码
        record.setPlateNumber(number);


        // 停车场记录查询车牌号码
        List<CarParkingRecord> carParkingRecordList = recordDao.queryByPlateNumber(number);
        if(carParkingRecordList.size() != 0 ){
            CarParkingRecord carParkingRecord = carParkingRecordList.get(0);


            // 如果说出场时间等于空 那么一定是出场
            if(Objects.isNull(carParkingRecord.getGmtOut())){

                // 判断当前出场的停车场id是否跟停车记录一致
                if(parkManage.getId() != carParkingRecord.getParkManageId()){
                    return R.fail("您当前在："+carParkingRecord.getParkManageName()+"停车场还没有出场");
                }

                // 出场 设置出场时间
                record.setGmtInto(carParkingRecord.getGmtInto());
                record.setGmtOut(new Date());
                record.setId(carParkingRecord.getId());
                // 设置出场金额
                BigDecimal bigDecimal = calcCarPark(record,parkManage);
                record.setCost(bigDecimal);
                if (recordDao.updateByPrimaryKeySelective(record)>0) {
                    return R.ok("出场成功");
                }else{
                    return R.fail("出场失败");
                }
            }

        }

        //查询车辆表里面的车辆类型根据车牌号码查询 停车记录里面设置车辆类型
        CarManage carManage = carManageDao.selectAllByPlateNumber(number);
        if( null == carManage ){
            // 如果车辆表里面没有查到就是临时车
            record.setType(CarType.TEMP_CAR.getCode());
        }else{
            record.setType(carManage.getType());
        }

        record.setGmtInto(new Date());
        record.setOrderNo(numberPlateUrl);
        if (recordDao.insert(record)>0) {
            return R.ok("车辆入场成功");
        }else{
            return R.fail("车辆入场失败");
        }
    }

    // 计算车辆费用
    private BigDecimal calcCarPark(CarParkingRecord record,ParkManage parkManage){
        // 入场以及出场时间
        Date gmtInto = record.getGmtInto();
        Date gmtOut = record.getGmtOut();
        // 停车场的的免费时长
        Integer freeTime = parkManage.getFreeTime();
        // 停车场收费最大金额
        BigDecimal maxMoney = parkManage.getMaxMoney();
        // 停车场计时单位
        BigDecimal unitCost = parkManage.getUnitCost();
        // 在一定分钟之内的费用
        Integer timeUnit = parkManage.getTimeUnit();

        // 查出入场跟出场相差的分钟
        long minutes = (gmtOut.getTime() - gmtInto.getTime()) / 1000 / 60;

        // 判断停车时长是否小于免费停车时长
        if (minutes<=freeTime) {
            return new BigDecimal(0);
        }
        // 否则 需要付费 算出超出的分钟时长
        long costTime = minutes - freeTime;
        // 把你停车的分钟能够跟一定分钟之内的费用整除 把得数返回 否则魔除以不等于0就把除数加1
        long unitCont = costTime % timeUnit == 0 ? costTime / timeUnit : costTime / timeUnit + 1;

        // 计算费用
        BigDecimal multiply = new BigDecimal(unitCont).multiply(unitCost);

        // 算出费用是否大于最大费用
        int i = multiply.compareTo(maxMoney);
        return i>0?maxMoney:multiply;
    }


}
