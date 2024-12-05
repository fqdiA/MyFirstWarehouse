package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.ParkManage;
import com.fq.superparking.entity.vo.ParkManageVo;
import com.fq.superparking.service.ParkManageService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("park/manager")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "停车场管理")
public class ParkManagerController {

    final ParkManageService park;

    @RequestMapping(value = "page",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("停车场分页以及名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "name",value = "停车场名字"),
            @ApiImplicitParam(name = "orgId",value = "单位id")
    })
    public R<PageInfo<ParkManage>> findByPage(
            @RequestParam(defaultValue = "1",value = "pageNum",required = false) Integer pageNum,
            @RequestParam(defaultValue = "10",value = "pageSize",required = false) Integer pageSize,
            String name,
            Integer orgId
    ){

        return park.findByPage(pageNum,pageSize,name,orgId);
    }

    @PostMapping("insert")
    @ApiOperation("增加停车场")
    public R<String> insert(ParkManage parkManage){
        return park.insert(parkManage);
    }

    @PostMapping("update")
    @ApiOperation("修改停车场")
    public R<String> update(ParkManage parkManage){
        return park.update(parkManage);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除停车场")
    @ApiImplicitParam(name = "id",value = "停车场id",required = true)
    public R<String> delete(@PathVariable Long id){
        Assert.notNull(id,"id不能为空");
        return park.deleteById(id);
    }   

    @GetMapping("{id}")
    @ApiOperation("根据id查询单个停车场")
    public R<ParkManage> getById(@PathVariable Long id){
        return park.findById(id);
    }

    @GetMapping("list")
    @ApiOperation("查询停车场id和名称列表")
    public R<List<ParkManageVo>> getParkAndNameList(){
        return park.listIdAndName();
    }


    @GetMapping("count")
    @ApiOperation("查询停车场记录数")
    public R<Integer> count(){
        return R.ok(park.getCount());
    }

    @RequestMapping("deleteS")
    @ApiOperation("多选删除停车场")
    public R<Integer> deleteS(String deletes){
        return park.deleteS(deletes);
    }


}
