package com.fq.superparking.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fq.superparking.entity.SysUser;
import com.fq.superparking.utils.JwtUtil;
import com.fq.superparking.utils.RedisUtil;
import com.fq.superparking.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 令牌拦截器
 * 从请求头和Cookie中获取token
 *
 * @author Administrator
 * @date 2023/08/10
 */
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    public static final String SUPER_PARKING_TOKEN = "super_parking_token";
    final JwtUtil jwtUtils;

    final RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;

        // 从请求头中获取token
        String tokenFromHeader = request.getHeader("Authorization");
        if (tokenFromHeader != null && tokenFromHeader.startsWith("Bearer ")) {
            token = tokenFromHeader.substring(7); // 去掉 "Bearer " 前缀
        }

        // 如果请求头中没有token，则尝试从Cookie中获取
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (SUPER_PARKING_TOKEN.equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }


        // 如果token为空,拦截请求
        if (!StringUtils.hasText(token)) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "50008");
            map.put("msg", "token为空");
            response(response, map);
            return false;
        } else {
            // 校验token是否有效
            if (jwtUtils.verifyToken(token)) {
                // 从token中解析用户信息
                String userId = jwtUtils.getUserIdFromId(token);
                // 从redis中获取用户对象
                SysUser user = (SysUser) redisUtil.getValue("user:"+userId);
                // 为了方便后续代码中随时获取用户信息
                // 我们把用户对象放入ThreadLocal中
                ThreadLocalUtil.set("user", user);
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", "50008");
                map.put("msg", "token无效或者过期");
                response(response, map);
                return false;
            }
        }

        // 将token存入请求属性，以便后续的处理
        request.setAttribute("token", token);
        return true; // 继续处理请求
    }

    // 返回给前端
    private void response(HttpServletResponse response, Map<String, Object> map) throws IOException {
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(map));
    }
}
