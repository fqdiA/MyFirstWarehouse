package com.fq.superparking.service.impl;

import com.fq.superparking.common.R;
import com.fq.superparking.dao.SysLogDao;
import com.fq.superparking.entity.SysLog;
import com.fq.superparking.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {
    final SysLogDao logDao;
    @Override
    public R<PageInfo<SysLog>> getLogInfoList(Integer pageNum, Integer pageSize, String username) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysLog> sysLogs = logDao.queryAllByUsername(username);
        return R.ok(new PageInfo<>(sysLogs));
    }
}
