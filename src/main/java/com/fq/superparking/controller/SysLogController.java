package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysLog;
import com.fq.superparking.service.SysLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@Api(tags = "系统日志接口")
@RequiredArgsConstructor
@Slf4j
public class SysLogController {
    final SysLogService logService;


    @ApiOperation("获取用户日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "username",value = "登陆的人的姓名")
    })
    @RequestMapping(value = "logList",method = {RequestMethod.GET,RequestMethod.POST})
    public R<PageInfo<SysLog>> getList(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            String username
    ){
        R<PageInfo<SysLog>> logInfoList = logService.getLogInfoList(pageNum, pageSize, username);
        return logInfoList;
    }
}
