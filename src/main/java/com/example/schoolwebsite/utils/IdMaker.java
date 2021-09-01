package com.example.schoolwebsite.utils;

import java.util.Date;

public class IdMaker {
    public static Integer BranchIdMaker(){
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.toString().substring(date.toString().length()-4));
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10)); }
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer ProfessionIdMaker(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10)); }
        return Integer.parseInt(stringBuilder.toString());
    }
}
