package com.fq.superparking.service.impl;

import com.fq.superparking.common.R;
import com.fq.superparking.dao.ParkManageDao;
import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.ParkManageVo;
import com.fq.superparking.service.ParkManageService;
import com.fq.superparking.utils.ThreadLocalUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ParkManageServiceImpl implements ParkManageService {

    final ParkManageDao mapper;

    @Override
    public R<PageInfo<ParkManage>> findByPage(Integer pageNum, Integer pageSize, String name,Integer orgId) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<ParkManage> pageInfo = new PageInfo<>(mapper.queryAll(name,orgId));
        return R.ok(pageInfo);
    }

    @Override
    public R<String> insert(ParkManage parkManage) {
        parkManage.setGmtCreate(new Date());
        SysUser user = (SysUser) ThreadLocalUtil.get("user");
        parkManage.setUserIdCreate(user.getUserId());
        int i = mapper.insertSelective(parkManage);
        if(i >0){
            return R.ok("添加成功");
        }
        return R.fail("添加失败");
    }

    @Override
    public R<String> update(ParkManage parkManage) {
        parkManage.setGmtModified(new Date());
        int i = mapper.updateByPrimaryKeySelective(parkManage);
        if (i>0) {
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

    @Override
    public R<String> deleteById(Long id) {
        int i = mapper.deleteByPrimaryKey(id);
        if (i>0) {
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    public R<ParkManage> findById(Long id) {
        ParkManage parkManage = mapper.selectByPrimaryKey(id);
        if(Objects.nonNull(parkManage)){
            return R.ok(parkManage);
        }
        return R.fail();
    }

    @Override
    public R<List<ParkManageVo>> listIdAndName() {
        List<ParkManageVo> parkManageVos = mapper.listIdAndName();
        if (Objects.nonNull(parkManageVos)) {
            return R.ok(parkManageVos);
        }
        return R.fail();
    }

    @Override
    public Integer getCount() {
        return mapper.getCount();
    }

    @Override
    public R<Integer> deleteS(String deletes) {
        Integer i = mapper.deleteS(deletes);
        if(i>0){
            return R.ok(i);
        }
        return R.fail(i);
    }
}
