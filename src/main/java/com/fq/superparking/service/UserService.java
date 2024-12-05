package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.SysUserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户服务层接口
 * @author fang
 * @date 2023/10/16
 */
public interface UserService {


    /**
     * 按id选择
     *
     * @param userId
     * @return {@link SysUser}
     */
    R selectById(Long userId);

    /**
     * 登录
     *
     * @param username  用户名
     * @param password  密码
     * @param ipAddress ip地址
     * @return {@link R}
     */
    R login(String username,String password,String ipAddress);


    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param orgId    组织单位id
     * @return {@link R}<{@link PageInfo}<{@link SysUser}>>
     */
    R<PageInfo<SysUser>> queryUserInfoByUserName(Integer pageNum, Integer pageSize, String username, Integer orgId);


    /**
     * 更新用户信息无密码
     *
     * @param sysUserVo 系统用户vo
     * @return {@link R}<{@link String}>
     */
    R<String> updateInfoNoPassword(SysUserVo sysUserVo);

    /**
     * 更新密码
     *
     * @param sysUserVo 系统用户vo
     * @return {@link R}<{@link String}>
     */
    R<String> updatePassword(SysUserVo sysUserVo);

    /**
     * 添加信息
     *
     * @param sysUserVo 系统用户vo
     * @return {@link R}<{@link String}>
     */
    R<String> addInfo(SysUserVo sysUserVo);

    /**
     * 按id查询用户信息
     *
     * @param id id
     * @return {@link SysUserVo}
     */
    SysUserVo queryInfoById(Long id);


    /**
     * 删除信息
     *
     * @param id id
     * @return {@link R}<{@link String}>
     */
    R<String> deleteInfo(Long id);

    /**
     * 查询用户id和用户名
     *
     * @return {@link R}<{@link SysUser}>
     */
    R<List<SysUser>> queryUserIdAndUsername();
}
