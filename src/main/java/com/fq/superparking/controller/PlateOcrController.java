package com.fq.superparking.controller;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.CarParkingRecord;
import com.fq.superparking.service.CarRecordService;
import com.fq.superparking.utils.AliOSSUtil;
import com.fq.superparking.utils.OcrUtil;
import com.fq.superparking.utils.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("plate")
@RequiredArgsConstructor
@Api(tags = "车牌识别")
public class PlateOcrController {

    final CarRecordService recordService;

    final AliOSSUtil aliOSSUtil;


    @PostMapping("upload")
    @ApiOperation("上传车牌")
    public R upload(MultipartFile file, String parkID, HttpServletRequest request) throws IOException {

        //获取到的停车场id是
        log.info("获取到的停车场id是{}",parkID);
        //获取车牌号
        String number = OcrUtil.plateLicense(file.getBytes());
        log.info("获取车牌号是{}",number);
        // 上传到oss中存储
        String fileName = aliOSSUtil.upload(file);
        // 进行出场或者入场
        return recordService.insert(fileName,number,Long.valueOf(parkID));
    }



}
