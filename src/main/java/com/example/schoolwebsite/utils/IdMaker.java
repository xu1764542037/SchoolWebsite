package com.example.schoolwebsite.utils;

import java.util.Date;

public class IdMaker {
    public static Integer BranchIdMaker(){
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.toString().substring(date.toString().length()-4));
        for (int i = 0; i < 2; i++) { stringBuilder.append((int)Math.floor(Math.random()*10)); }
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer ProfessionIdMaker(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10)); }
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer ClassIdMaker(Integer ProfessionId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ProfessionId);
        for (int i = 0; i < 2; i++) { stringBuilder.append((int)Math.floor(Math.random()*10));}
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer CourseIdMaker(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10));}
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer Teacher_CourseIdMaker(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) { stringBuilder.append((int)Math.floor(Math.random()*10));}
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer TeacherIdMaker(Integer branch){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("30");
        stringBuilder.append(branch.toString().substring(branch.toString().length()-3));
        for (int i = 0; i < 3; i++) { stringBuilder.append((int)Math.floor(Math.random()*10));}
        return Integer.parseInt(stringBuilder.toString());
    }
    public static Integer StudentIdMaker(Integer branch,Integer classes){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((new Date()).toString().substring((new Date()).toString().length()-2)).trim());
        stringBuilder.append(classes.toString().trim());
        for (int i = 0; i < 2; i++) {stringBuilder.append((int)Math.floor(Math.random()*10));}
        return Integer.parseInt(stringBuilder.toString());
    }
}
