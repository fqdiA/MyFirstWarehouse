package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.dto.OrgDTO;
import com.fq.superparking.entity.vo.SysOrgVo;
import com.fq.superparking.service.OrgService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("org")
@Api(tags = "组织管理")
public class OrgController {

    final OrgService orgService;

    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("分页查询组织列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "组织名称"),
            @ApiImplicitParam(name = "pageNum",value = "当前第几页"),
            @ApiImplicitParam(name = "pageSize",value = "一页大小")
    })
    public R<PageInfo<SysOrg>> findByPage(
    String name,
    @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
    @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize
    ){
        R<PageInfo<SysOrg>> all = orgService.findAll(pageNum, pageSize, name);
        return all;
    }


    @PostMapping("insert")
    @ApiOperation("新增组织单位")
    public R<String> insert(@RequestBody SysOrgVo vo){
        return orgService.insert(vo);
    }

    @PostMapping("update")
    @ApiOperation("修改组织单位")
    public R<String> update(@RequestBody SysOrgVo vo){
        return orgService.update(vo);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除组织单位")
    @ApiImplicitParam(name = "id",value = "组织单位id",required = true)
    public R<String> delete(@PathVariable("id") Long id){
        Assert.notNull(id,"组织id不能为空");
        return orgService.deleteById(id);
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询")
    public R<SysOrg> getInfoById(@PathVariable Integer id){
        return orgService.findById(id);
    }

    @GetMapping("list")
    @ApiOperation("获取组织ID和名称列表")
    public R<List<OrgDTO>> orgList(){
        R<List<OrgDTO>> list = orgService.list();
        return list;
    }

    @GetMapping("count")
    @ApiOperation("组织总行数")
    public R<Long> count(){
        return R.ok(orgService.queryCount());
    }
}
