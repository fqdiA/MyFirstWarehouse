package com.fq.superparking.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.fq.superparking.common.R;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("phone")
@Api(tags = "手机验证码登录接口")
@RequiredArgsConstructor
public class PhoneController {

    final RedisUtil redisUtil;

    final JwtUtil jwtUtil;

    /**
     * 手机登录
     * 通过手机验证码登录
     *
     * @param phoneCode 电话号码
     * @param phone     电话
     * @param response  响应
     * @return {@link R}<{@link String}>
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation("通过手机验证码登录")
    @ApiImplicitParam(name = "code",value = "前端传过来的验证码")
    public R phoneLogin(String phoneCode,String phone , HttpServletResponse response){
        Assert.hasText(phone,"请输入手机号码");
        Assert.hasText(phoneCode,"请输入短信验证码");

        Object value = redisUtil.getValue(phone);
        if(phoneCode.equals(value)){
            String ipToken = jwtUtil.setToken(phone);
            response.addCookie(new Cookie("super_parking_token",ipToken));
            SysUser sysUser = new SysUser();
            sysUser.setToken(ipToken);
            return R.ok(sysUser);
        }
        return R.fail("验证码或者当前手机号错误");
    }


    @RequestMapping(value = "phoneCode",method = RequestMethod.POST)
    @ApiOperation("发送短信验证码接口")
    public R<String> phoneCode(String phone ) throws Exception {
        Assert.hasText(phone,"请输入手机号码");

        String numbers = RandomUtil.randomNumbers(6);

        SendSMS.sendSms(phone,numbers);

        redisUtil.setKey(phone,numbers,60);


        return R.ok("发送短信验证码成功");
    }


}
