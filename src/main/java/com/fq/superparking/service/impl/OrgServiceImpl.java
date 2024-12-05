package com.fq.superparking.service.impl;

import com.fq.superparking.common.R;
import com.fq.superparking.dao.SysOrgDao;
import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.dto.OrgDTO;
import com.fq.superparking.entity.vo.SysOrgVo;
import com.fq.superparking.service.OrgService;
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

/**
 * @author fang
 * @version 1.0.0
 * @date 2023/10/21
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class OrgServiceImpl implements OrgService {

    final SysOrgDao orgDao;

    @Override
    public R<PageInfo<SysOrg>> findAll(Integer pageNum,Integer pageSize,String name) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysOrg> sysOrgs = orgDao.queryAllByName(name);
        return R.ok(new PageInfo<>(sysOrgs));
    }

    @Override
    public R<String> insert(SysOrgVo vo) {
        SysOrg sysOrg = new SysOrg();
        //vo 对象里面的参数拷贝到sysOrg试题对象里面
        BeanUtils.copyProperties(vo,sysOrg);
        //把vo对象里面没有的值 进行属性补全
        if (Objects.isNull(sysOrg.getParentId())) {
            sysOrg.setParentId(0L);
        }
        sysOrg.setGmtCreate(new Date());
        if (orgDao.insert(sysOrg)>0) {
            return R.ok("新增成功");
        }
        return R.fail("新增失败");
    }

    @Override
    public R<String> update(SysOrgVo vo) {
        SysOrg sysOrg = new SysOrg();
        BeanUtils.copyProperties(vo,sysOrg);
        sysOrg.setGmtModified(new Date());
        if (orgDao.update(sysOrg)>0) {
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

    @Override
    public R<String> deleteById(Long id) {
        if (orgDao.deleteById(id)>0) {
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    public R<SysOrg> findById(Integer id) {
        return R.ok(orgDao.queryById(Long.valueOf(id)));
    }

    @Override
    public R<List<OrgDTO>> list() {
        return R.ok(orgDao.getList());
    }

    @Override
    public Long queryCount() {
        return orgDao.count(new SysOrg());
    }


}
