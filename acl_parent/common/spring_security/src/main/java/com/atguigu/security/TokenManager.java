package com.atguigu.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    private long liveTime=24*60*60*1000;

    private String tokenKey="123456";


    //1.根据用户名生成token
    public String createToken(String username){
        String token= Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis()+liveTime))
                .signWith(SignatureAlgorithm.ES512,tokenKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }
    //2.根据token字符串得到用户信息
    public String getUserInfoFromToken(String token){
        String userInfo=Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody().getSubject();
        return userInfo;
    }
    //3.删除token
    public void deleteToken(String token){

    }


}
