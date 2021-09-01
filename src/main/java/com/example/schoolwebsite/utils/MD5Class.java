package com.example.schoolwebsite.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Class {
    public static String NewPassword(String OldPassword){//密码加密
        if ("".equals(OldPassword) || OldPassword==null){
            return null;
        }
        StringBuilder Password = new StringBuilder(OldPassword);
        StringBuilder NewPwd = new StringBuilder();
        Password.append(Password);//密码加倍
        try {
            MessageDigest Md = MessageDigest.getInstance("md5");//实例化Md5操作对象
            byte[] BT = Password.toString().getBytes("utf-8");
            Md.digest(BT);
            for (byte b:BT){
                NewPwd.append(Integer.toHexString(b & 0xff));//将密码的byte数组转化为16进制字符串
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return "";
        }
        return NewPwd.toString();
    }
}
