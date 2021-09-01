package com.example.schoolwebsite.utils;

import java.util.Date;

public class IdMaker {
    public static Integer BranchIdMaker(){
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.toString().substring(date.toString().length()-4,date.toString().length()));
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10)); }
        return Integer.parseInt(stringBuilder.toString());
    }
}
