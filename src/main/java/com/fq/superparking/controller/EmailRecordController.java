package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarParkingRecord;
import com.fq.superparking.entity.EmailRecord;
import com.fq.superparking.service.EmailRecordService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("email")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "邮件推送管理")
public class EmailRecordController {
    final EmailRecordService emailRecordService;




    @PostMapping("page")
    @ApiOperation("分页查询邮件记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "emailMotif",value = "邮件主题")
    })
    public R<PageInfo<EmailRecord>> findByPage(
            @RequestParam(value = "pageNum" , required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize" , required = false, defaultValue = "10") Integer pageSize,
            String emailMotif
    ){
        return emailRecordService.queryAllPage(pageNum,pageSize,emailMotif);
    }

    @RequestMapping(value = "sendEmail",method = {RequestMethod.POST})
    @ApiOperation("发送邮件")
    @ApiImplicitParam(name = "emailRecord",value = "邮件信息")
    public R<String> sendEmail(EmailRecord emailRecord){
        R<String> stringR = emailRecordService.addEmailRecordInfo(emailRecord);
        return stringR;
    }

        @RequestMapping(value = "deleteEmailInfo/{id}",method = {RequestMethod.DELETE})
    @ApiOperation("删除邮件信息")
    @ApiImplicitParam(name = "id",value = "邮件信息id")
    public R<String> sendEmail(@PathVariable("id") Long id){
        R<String> stringR = emailRecordService.deleteEmailRecordInfo(id);
        return stringR;
    }
}
