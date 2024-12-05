package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarManage;
import com.fq.superparking.entity.SysOrg;
import com.fq.superparking.entity.vo.CarManageVo;
import com.fq.superparking.entity.vo.SysOrgVo;
import com.fq.superparking.service.CarManagerService;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = "车辆")
@RequestMapping("car")
public class CarManagerController {
    final CarManagerService carService;

    @RequestMapping(value = "page",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("分页查询车辆列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "车主或者车牌号名称"),
            @ApiImplicitParam(name = "pageNum",value = "当前第几页"),
            @ApiImplicitParam(name = "pageSize",value = "一页大小"),
            @ApiImplicitParam(name = "orgId",value = "组织单位id")
    })
    public R<PageInfo<CarManage>> findByPage(
            String name,
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            Integer orgId
    ){
        R<PageInfo<CarManage>> all = carService.findAll(pageNum, pageSize, name, orgId);
        return all;
    }


    @PostMapping("insert")
    @ApiOperation("新增车辆")
    public R<String> insert(@RequestBody CarManage car){
        return carService.insert(car);
    }

    @PostMapping("update")
    @ApiOperation("修改车辆信息")
    public R<String> update(@RequestBody CarManage car){
        return carService.update(car);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车辆")
    @ApiImplicitParam(name = "id",value = "车辆id",required = true)
    public R<String> delete(@PathVariable("id") Long id){
        Assert.notNull(id,"车辆id不能为空");
        return carService.deleteById(id);
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询车辆信息")
    public R<CarManage> getInfoById(@PathVariable Integer id){
        return carService.findById(id);
    }

    @GetMapping("count")
    @ApiOperation("查找车辆记录数")
    public R<Integer> getCount(){
        return R.ok(carService.getCount());
    }
}
