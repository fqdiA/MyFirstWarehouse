package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.entity.SysRole;
import com.fq.superparking.entity.vo.ParkManageVo;
import com.fq.superparking.entity.vo.SysRoleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色服务层接口
 *
 * @author fang
 * @version 1.0.0
 * @date 2023/10/23
 */
public interface RoleService {
    /**
     * 按页面查找
     * 分页查询 以及根据角色名字模糊查询
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param sign     角色标识
     * @return {@link R}<{@link PageInfo}<{@link SysRole}>>
     */
    R<PageInfo<SysRole>> findByPage(Integer pageNum, Integer pageSize, String sign);

    /**
     * 添加角色
     *
     * @param sysRole 角色信息
     * @return {@link R}<{@link String}>
     */
    R<String> insert(SysRole sysRole);

    /**
     * 更新角色
     *
     * @param sysRole 角色信息
     * @return {@link R}<{@link String}>
     */
    R<String> update(SysRole sysRole);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return {@link R}<{@link String}>
     */
    R<String> deleteById(Long id);

    /**
     * 根据id查询角色信息
     *
     * @param id 角色id
     * @return {@link R}<{@link SysRole}>
     */
    R<SysRoleVo> findById(Long id);

    /**
     * 查找角色id和角色名
     *
     * @return {@link R}<{@link List}<{@link SysRole}>>
     */
    R<List<SysRole>> findRoleIdAndRoleName();
}
