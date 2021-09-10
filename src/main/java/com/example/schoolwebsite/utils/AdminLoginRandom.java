package com.example.schoolwebsite.utils;

import java.util.Date;

public class AdminLoginRandom {
    public static String LoginRanDom(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new Date().toString().substring(new Date().toString().length()-2));
        for (int i = 0; i < 8; i++) {stringBuilder.append((int)Math.floor(Math.random()*10));}
        return stringBuilder.toString();
    }
}
