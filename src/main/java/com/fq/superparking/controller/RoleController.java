package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysRole;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.SysRoleVo;
import com.fq.superparking.service.RoleService;
import com.fq.superparking.utils.ThreadLocalUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("role")
//@Api(tags = "角色管理")
public class RoleController {
    final RoleService roleService;

    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum",value = "当前页"),
//            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
//            @ApiImplicitParam(name = "pageSize",value = "角色标识")
//    })
    public R<PageInfo<SysRole>> pageList(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            String sign){
        R<PageInfo<SysRole>> byPage = roleService.findByPage(pageNum, pageSize, sign);
        return byPage;
    }

    @PostMapping("insert")
    public R<String> insert(SysRole sysRole){
        sysRole.setGmtCreate(new Date());
        sysRole.setOrgId(-1L);
        SysUser user = (SysUser) ThreadLocalUtil.get("user");
        Long userId = user.getUserId();
        sysRole.setUserIdCreate(userId);
        return roleService.insert(sysRole);
    }

    @PostMapping("update")
    public R<String> update(SysRole sysRole){
        sysRole.setGmtModified(new Date());
        return roleService.update(sysRole);
    }

    @DeleteMapping("{id}")
    public R<String> delete( @PathVariable("id") Long id){
        Assert.notNull(id,"id不能为空");
        return roleService.deleteById(Long.valueOf(id));
    }

    @GetMapping("findById")
    public R<SysRoleVo> getById(Long id){
        return roleService.findById(id);
    }

    @GetMapping("findRoleIdAndRoleName")
//    @ApiImplicitParam("查找角色id和角色名")
    public R<List<SysRole>> findRoleIdAndRoleName(){
        return roleService.findRoleIdAndRoleName();
    }
}
