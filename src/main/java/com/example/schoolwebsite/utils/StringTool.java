package com.example.schoolwebsite.utils;

import org.apache.ibatis.annotations.Param;

public class StringTool {
    public static String StringToNumberString(String str){
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i <str.length() ; i++) {
            if (str.charAt(i)>47&&str.charAt(i)<58){
                str2.append(str.charAt(i));
            }
        }
        System.out.println(str2);
        return str2.toString();
    }
    public static Integer StringToNumberInteger(String str){
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i <str.length() ; i++) {
            if (str.charAt(i)>48&&str.charAt(i)<57){
                str2.append(str.charAt(i));
            }
        }
        return Integer.parseInt(str2.toString());
    }
}
