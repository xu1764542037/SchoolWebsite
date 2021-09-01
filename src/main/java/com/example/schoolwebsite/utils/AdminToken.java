package com.example.schoolwebsite.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class AdminToken {
//    private static final long Expire_Time = 24*60*60*1000;
//    //Token时长
//    private static final String Token_Secret = "OriginAdmin";
//    //Token密钥
//    public static String sign(Admin admin){
//        //签发Token
//        String Token = null;
//        try {
//            Date ExpireTime = new Date(System.currentTimeMillis()+Expire_Time);
//            Token = JWT.create()
//                    //添加Token携带的数据
//                    .withClaim("AdminName",admin.getAdminName())
//                    .withClaim("Login",admin.getLogin())
//                    .withClaim("CreateTime",admin.getCreateTime())
//                    .withClaim("Admin",true)
//                    //加密数据
//                    .withExpiresAt(ExpireTime)
//                    //过期信息
//                    .sign(Algorithm.HMAC256(Token_Secret));
//            //HMAC256为加密算法，括号里是加密信息
//        }catch (Exception e){
//            e.getMessage();
//        }
//        return Token;
//    }
//
//    public static boolean Verify(String Token){
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Token_Secret)).build();
//            verifier.verify(Token);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
}
