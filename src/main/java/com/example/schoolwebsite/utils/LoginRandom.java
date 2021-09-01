package com.example.schoolwebsite.utils;

import java.util.Random;
import java.util.UUID;

public class LoginRandom {
    //用户注册时生成随机账号，交予用户保存
    public static String CreateLogin(){
        StringBuilder tempStr = new StringBuilder();
        Random random = new Random();
        for (int i=0 ; i<12 ; i++){
           int a = random.nextInt(10);
            tempStr.append(a);
        }
        return tempStr.toString();
    }
    //注册时生成的Id值，用于在数据库赋值
    public static String CreateID() {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        return id.length() > 40 ? id.substring(0, 39) : id;
    }

}
