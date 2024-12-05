package com.fq.superparking.controller;

import com.fq.superparking.common.Constant;
import com.fq.superparking.common.R;
import com.fq.superparking.config.RedisConfig;
import com.fq.superparking.entity.SysUser;
import com.fq.superparking.entity.vo.SysUserVo;
import com.fq.superparking.service.UserService;
import com.fq.superparking.utils.ClientIPUtils;
import com.fq.superparking.utils.JwtUtil;
import com.fq.superparking.utils.RedisUtil;
import com.fq.superparking.utils.ThreadLocalUtil;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    final UserService userService;

    final RedisUtil redisUtil;

    final JwtUtil jwtUtil;



    @GetMapping("/captcha")
    @ApiOperation("验证码")
    public void code(HttpServletResponse response, HttpServletRequest request) throws IOException {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
        String text = captcha.text();

        // 可以使用客户端的IP地址 标识用户
        String ipAddress = ClientIPUtils.getIpAddress(request);
        redisUtil.setKey("code:"+ipAddress,text, Constant.CAPTCHA_EXPIRE_TIME);  //保存在redis里面
        response.setContentType("image/png");  //响应图片格式
        captcha.out(response.getOutputStream());
    }


    @PostMapping("/login")
    @ApiOperation("登陆验证")
    public R login(String username,String password,String code,String phoneCode,String phone,String index ,HttpServletRequest request,HttpServletResponse response){
        System.out.println("1".equals(index));
        if("1".equals(index)){
            Assert.hasText(phone,"请输入手机号码");
            Assert.hasText(phoneCode,"请输入短信验证码");

            Object value = redisUtil.getValue(phone);
            if(null == value){
                return R.fail("验证码过期");
            }
            if(phoneCode.equals(value)){


                R sysUserR = userService.selectById(null);

                SysUser data = (SysUser) sysUserR.getData();
                String token = jwtUtil.setToken(String.valueOf(data.getUserId()));
                data.setToken(token);
                redisUtil.setKey("user:"+data.getUserId(),data,Constant.USER_EXPIRE_TIME);
                response.addCookie(new Cookie("super_parking_token",token));
                return R.ok(data);
            }
            return R.fail("验证码或者当前手机号错误");
        }

        //断言全局捕捉异常
        Assert.hasText(username,"用户名不能为空");
        Assert.hasText(password,"密码不能为空");
        Assert.hasText(code,"验证码不能为空");

            String ipAddress = ClientIPUtils.getIpAddress(request);

        if(Objects.isNull(redisUtil.getValue("code:" + ipAddress))){
            return R.fail("验证码过期");
        }

        String value = redisUtil.getValue("code:" + ipAddress).toString();

        if(Objects.equals(code,value)){
            R r = userService.login(username, password,ipAddress);
            if(Objects.equals(r.getCode(),200)){
                SysUser user = (SysUser) r.getData();
                //生成token  id跟用户名加密
                String token = jwtUtil.setToken(String.valueOf(user.getUserId()), user.getUsername());
                //把token通过cookie返回给前端
                response.addCookie(new Cookie("super_parking_token",token));
                user.setToken(token);
                return R.ok(user);
            }else{
                return R.fail("用户名或者密码错误");
            }
        }
        return R.fail("验证码错误");
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public R<SysUser> getUserInfo(String token){
        // 获取用户信息
        SysUser user = (SysUser) ThreadLocalUtil.get("user");
        System.out.println(token);
        return R.ok(user);
    }

    //退出登录  把用户信息移除
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public R<String> logout(){
        SysUser user = (SysUser) ThreadLocalUtil.get("user");
        Long userId = user.getUserId();
        if (redisUtil.deleteKey("user:"+userId.toString())) {
            return R.ok("退出成功");
        }
        return R.fail("退出失败");
    }

    @GetMapping("/getInfo")
    @ApiOperation("得到用户所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小"),
            @ApiImplicitParam(name = "username",value = "用户名")
    })
    public R<PageInfo<SysUser>> getInfo(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            String username,
            Integer orgId){
        R<PageInfo<SysUser>> pageInfoR = userService.queryUserInfoByUserName(pageNum, pageSize, username, orgId);
        return pageInfoR;
    }


    @ApiOperation("更新用户信息")
    @ApiImplicitParam(name = "sysUserVo",value = "用户信息")
    @PostMapping("updateInfo")
    public R<String> updateInfoNoPassword(SysUserVo sysUserVo){

        return userService.updateInfoNoPassword(sysUserVo);
    }

    @ApiOperation("更新用户密码")
    @ApiImplicitParam(name = "sysUserVo",value = "用id跟密码")
    @PostMapping("updatePassword")
    public R<String> updatePassword(SysUserVo sysUserVo){
        return userService.updatePassword(sysUserVo);
    }

    @PostMapping("addInfo")
    @ApiOperation("添加用户信息")
    @ApiImplicitParam(name = "sysUserVo",value= "用户信息")
    public R<String> addInfo(SysUserVo sysUserVo){
        return userService.addInfo(sysUserVo);
    }

    @GetMapping("getInfoById")
    @ApiOperation("查询用户id")
    @ApiImplicitParam(name = "id",value = "用户id")
    public R<SysUserVo> getInfoById(String id){
        SysUserVo sysUserVo = userService.queryInfoById(Long.valueOf(id));
        return R.ok(sysUserVo);
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id",value = "用户id")
    public R<String> deleteInfoById(@PathVariable("id") Long id){
        R<String> stringR = userService.deleteInfo(Long.valueOf(id));
        return stringR;
    }

    @GetMapping("idAndUsernameList")
    @ApiOperation("查询用户id跟用户名")
    public R<List<SysUser>> queryIdAndUsernameList(){
        return userService.queryUserIdAndUsername();
    }


}
