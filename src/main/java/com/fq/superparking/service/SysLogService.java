package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysLogService {
    /**
     * 获取日志登录信息列表
     *
     * @param pageNum  当前页
     * @param pageSize 页面大小
     * @param username 登录用户名用户名
     * @return {@link R}<{@link PageInfo}<{@link SysLog}>>
     */
    R<PageInfo<SysLog>> getLogInfoList(Integer pageNum,Integer pageSize,String username);
}
