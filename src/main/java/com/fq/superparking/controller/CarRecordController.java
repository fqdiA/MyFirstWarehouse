package com.fq.superparking.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarParkingRecord;
import com.fq.superparking.service.CarRecordService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("record")
@RestController
@Api(tags = "停车记录")
public class CarRecordController {
    final CarRecordService recordService;

    @PostMapping("page")
    @ApiOperation("分页查询停车记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "plateNumber",value = "车牌号码"),
            @ApiImplicitParam(name = "orgId",value = "组织Id"),
            @ApiImplicitParam(name = "parkManageId",value = "停车场id"),
            @ApiImplicitParam(name = "IntoTime",value = "入场时间"),
            @ApiImplicitParam(name = "OutTime",value = "出场时间")
    })
    public R<PageInfo<CarParkingRecord>> findByPage(
            @RequestParam(value = "pageNum" , required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize" , required = false, defaultValue = "10") Integer pageSize,
            String plateNumber, Long orgId, Long parkManageId,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date IntoTime,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date OutTime
            ){
        System.out.println(IntoTime);
        System.out.println(OutTime);
        return recordService.findAll(pageNum, pageSize, plateNumber, orgId, parkManageId,IntoTime,OutTime);
    }
}
