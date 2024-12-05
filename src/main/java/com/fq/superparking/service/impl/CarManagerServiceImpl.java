package com.fq.superparking.service.impl;

import com.fq.superparking.common.CarType;
import com.fq.superparking.common.R;
import com.fq.superparking.dao.CarManageDao;
import com.fq.superparking.entity.CarManage;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.CarManageVo;
import com.fq.superparking.service.CarManagerService;
import com.fq.superparking.utils.ThreadLocalUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CarManagerServiceImpl implements CarManagerService {

    final CarManageDao carManageDao;

    @Override
    public R<PageInfo<CarManage>> findAll(Integer pageNum, Integer pageSize, String name,Integer orgId) {
        PageHelper.startPage(pageNum,pageSize);
        List<CarManage> all = carManageDao.findAll(name,orgId);

        all.forEach(value->{
            for (CarType type: CarType.values()) {
                if(type.getCode().equals(value.getType())){
                    value.setTypeName(type.getValue());
                }
            }
        });

        PageInfo<CarManage> carManageVoPageInfo = new PageInfo<>(all);
        return R.ok(carManageVoPageInfo);
    }

    @Override
    public R<String> insert(CarManage car) {
        if (car.getGender().equals("1")) {
            car.setGender("男");
        }else{
            car.setGender("女");
        }
        car.setName(car.getNickname());
        car.setGmtCreate(new Date());  //添加时间（创建时间）
        SysUser user = (SysUser) ThreadLocalUtil.get("user");  //获取创建人id
        if(Objects.nonNull(user.getUserId())){
            car.setUserIdCreate(user.getUserId());
        }
        if (carManageDao.insert(car)>0) {
            return R.ok("新增车辆成功");
        }
        return R.fail("新增车辆失败");
    }

    @Override
    public R<String> update(CarManage car) {
        if (car.getGender().equals("1")) {
            car.setGender("男");
        }else{
            car.setGender("女");
        }
        car.setGmtModified(new Date());
        car.setName(car.getNickname());
        if (carManageDao.updateByPrimaryKeySelective(car)>0) {
            return R.ok("修改车辆成功");
        }
        return R.fail("修改车辆失败");
    }

    @Override
    public R<String> deleteById(Long id) {
        if (carManageDao.deleteByPrimaryKey(id)>0) {
            return R.ok("删除车辆成功");
        }
        return R.fail("删除车辆失败");
    }

    @Override
    public R<CarManage> findById(Integer id) {
        CarManage carManage = carManageDao.selectByPrimaryKey(Long.valueOf(id));
        if (Objects.nonNull(carManage)) {
            return R.ok(carManage);
        }
        return R.fail();
    }

    @Override
    public Integer getCount() {
        return carManageDao.getCount();
    }
}