package com.fq.superparking.service.impl;

import com.fq.superparking.common.R;
import com.fq.superparking.dao.EmailRecordDao;
import com.fq.superparking.dao.SysUserDao;
import com.fq.superparking.entity.EmailRecord;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.service.EmailRecordService;
import com.fq.superparking.utils.RedisUtil;
import com.fq.superparking.utils.SendEmailUtil;
import com.fq.superparking.utils.ThreadLocalUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmailRecordServiceImpl implements EmailRecordService {

    final EmailRecordDao emailRecordDao;

    final SysUserDao sysUserDao;

    @Override
    public R<PageInfo<EmailRecord>> queryAllPage(Integer pageNum, Integer pageSize, String emailMotif) {
        PageHelper.startPage(pageNum,pageSize);
        List<EmailRecord> emailRecords = emailRecordDao.queryAllPage(emailMotif);
        PageInfo<EmailRecord> pageInfo = new PageInfo<>(emailRecords);
        return R.ok(pageInfo);
    }

    @Override
    public R<String> addEmailRecordInfo(EmailRecord emailRecord) {
        // 本地线程拿出当前账户信息
        SysUser user = (SysUser) ThreadLocalUtil.get("user");

        // 判断输入收件人的邮箱是否是空
        if(null != emailRecord.getAddresseeUserEmail()){
            emailRecord.setSendUserId(user.getUserId());
            emailRecord.setSendUserUsername(user.getUsername());
            emailRecord.setSendUserEmail(user.getEmail());
            emailRecord.setSendTime(new Date());
        }else{
            // 如果前端下拉框的id不为空 进来
            // 查出收件人的信息
            SysUser sysUser = sysUserDao.selectByPrimaryKey(emailRecord.getAddresseeUserId());
            emailRecord.setSendUserId(user.getUserId());
            emailRecord.setAddresseeUserEmail(sysUser.getEmail());
            emailRecord.setAddresseeUserUsername(sysUser.getUsername());
            emailRecord.setSendUserUsername(user.getUsername());
            emailRecord.setSendUserEmail(user.getEmail());
            emailRecord.setSendTime(new Date());
        }

        String[] addresseeUserEmail = emailRecord.getAddresseeUserEmail().split(",");
        try {
            SendEmailUtil.sendEmail(addresseeUserEmail,emailRecord.getEmailMotif(),emailRecord.getEmailSubject());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        int i = emailRecordDao.insertSelective(emailRecord);
        if(i>0){
            return R.ok("发送成功");
        }
        return R.fail("发送失败");
    }

    @Override
    public R<String> deleteEmailRecordInfo(Long id) {
        int i = emailRecordDao.deleteByPrimaryKey(id);
        if(i>0){
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

}
