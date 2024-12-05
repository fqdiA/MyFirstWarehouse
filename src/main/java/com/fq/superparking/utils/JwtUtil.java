package com.fq.superparking.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;


@Component
public class JwtUtil {
    //盐
    @Value("${jwt.secret}")
    private String secret;
    //一周过期时间 单位是毫秒
    @Value("${jwt.expiration}")
    private Long expiration;

    //使用账号密码登陆 设置token
    public String setToken(String userId,String userName){
        Date now = new Date();

        Date expirationDate = new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setId(userId)
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    // 使用手机验证码登录生成的token
    public String setToken(String phone){
        Date now = new Date();

        Date expirationDate = new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setId(phone)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    //获取token id
    public String getUserIdFromId(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody().getId();
    }

    //获取token name
    public String getUserNameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    //校验token
    public boolean verifyToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
