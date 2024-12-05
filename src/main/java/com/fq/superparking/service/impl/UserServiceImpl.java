package com.fq.superparking.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.fq.superparking.common.Constant;
import com.fq.superparking.common.R;
import com.fq.superparking.dao.SysLogDao;
import com.fq.superparking.dao.SysUserDao;
import com.fq.superparking.entity.SysLog;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.SysUserVo;
import com.fq.superparking.service.UserService;
import com.fq.superparking.utils.JwtUtil;
import com.fq.superparking.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 用户业务层实现
 * @author fang
 * @date 2023/10/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    final SysUserDao userDao;

    final RedisUtil redisUtil;

    final JwtUtil jwtUtil;

    // 登录日志
    final SysLogDao logDao;

    @Override
    public R selectById(Long userId) {
        return R.ok(userDao.selectByPrimaryKey(1L));
    }

    @Override
    public R login(String username, String password,String ipAddress) {
        log.info("进行用户登录");
        SysUser user = userDao.queryByUsername(username);
        String cryptoPass = SecureUtil.md5(password);
        if(Objects.equal(user.getPassword(),cryptoPass)){
            SysLog sysLog = new SysLog();
            sysLog.setUserId(user.getUserId());
            sysLog.setUsername(user.getUsername());
            sysLog.setOperation("登录");
            sysLog.setTime(10);
            sysLog.setIp(ipAddress);
            sysLog.setDeviceType(0);
            sysLog.setLogType(0);
            sysLog.setGmtCreate(new Date());
            logDao.insertSelective(sysLog);
            //用户信息放到redis里面 一天过期
            redisUtil.setKey("user:"+user.getUserId().toString(),user, Constant.USER_EXPIRE_TIME);
            return R.ok(user);
        }
        return R.fail("用户名或者密码错误！");
    }

    @Override
    public R<PageInfo<SysUser>> queryUserInfoByUserName(Integer pageNum,Integer pageSize,String username,Integer orgId) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUserList = userDao.selectInfoByUserName(username,orgId);

        if (null != sysUserList){
            PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
            return R.ok(pageInfo);
        }
        return R.fail();
    }

    @Override
    public R<String> updateInfoNoPassword(SysUserVo sysUserVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo,sysUser);
        sysUser.setGmtModified(new Date());
        int update = userDao.updateByPrimaryKeySelective(sysUser);
        if(update >= 1){
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

    @Override
    public R<String> updatePassword(SysUserVo sysUserVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo,sysUser);
        sysUser.setGmtModified(new Date());
        sysUser.setPassword(SecureUtil.md5(sysUserVo.getPassword()));
        int update = userDao.updateByPrimaryKeySelective(sysUser);
        if(update >= 1){
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

    @Override
    public R<String> addInfo(SysUserVo sysUserVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo,sysUser);
        sysUser.setGmtCreate(new Date());
        sysUser.setAvatarStatus(0);
        sysUser.setPassword(SecureUtil.md5(sysUserVo.getPassword()));
        int insert = userDao.insertSelective(sysUser);
        if(insert >= 1){
            return R.ok("添加成功");
        }
        return R.fail("添加失败");
    }

    @Override
    public SysUserVo queryInfoById(Long id) {
        SysUser sysUser = userDao.selectByPrimaryKey(id);
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return sysUserVo;
    }

    @Override
    public R<String> deleteInfo(Long id) {
        int i = userDao.deleteByPrimaryKey(id);
        if(i>0){
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    public R<List<SysUser>> queryUserIdAndUsername() {
        return R.ok(userDao.selectUserIdAndUsername());
    }


}
