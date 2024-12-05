package com.fq.superparking.service.impl;

import com.fq.superparking.common.R;
import com.fq.superparking.dao.SysRoleDao;
import com.fq.superparking.entity.SysRole;
import com.fq.superparking.entity.vo.SysRoleVo;
import com.fq.superparking.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    // 注入角色的数据访问层
    final SysRoleDao roleDao;

    @Override
    public R<PageInfo<SysRole>> findByPage(Integer pageNum, Integer pageSize, String sign) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roleDao.queryAllBySign(sign));
        if (pageInfo.getList().size() != 0) {
            return R.ok(pageInfo);
        }
        return R.fail();
    }

    @Override
    public R<String> insert(SysRole sysRole) {
        int inserted = roleDao.insertSelective(sysRole);
        if(inserted>0){
            return R.ok("添加成功");
        }
        return R.fail("添加失败");
    }

    @Override
    public R<String> update(SysRole sysRole) {
        if (roleDao.updateByPrimaryKeySelective(sysRole)>0) {
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

    @Override
    public R<String> deleteById(Long id) {
        if (roleDao.deleteByPrimaryKey(id)>0) {
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    public R<SysRoleVo> findById(Long id) {
        SysRole sysRole = roleDao.selectByPrimaryKey(id);
        SysRoleVo sysRoleVo = new SysRoleVo();
        BeanUtils.copyProperties(sysRole,sysRoleVo);
        if (Objects.nonNull(sysRole)) {
            return R.ok(sysRoleVo);
        }
        return R.fail();
    }

    @Override
    public R<List<SysRole>> findRoleIdAndRoleName() {
        return R.ok(roleDao.queryIdAndName());
    }
}
