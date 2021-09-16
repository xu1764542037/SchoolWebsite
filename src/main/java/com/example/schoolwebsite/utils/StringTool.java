package com.example.schoolwebsite.utils;

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
    //参数不为空
    public static boolean NotNullStringCheck(String str){
        return !"".equals(str);
    }
    public static boolean NotNullStringCheck(Integer integer){
        return integer!=null;
    }
    public static boolean NotNullStringCheck(String str,Integer integer){
        return !"".equals(str)&&integer!=null;
    }
    public static boolean NotNullStringCheck(String str1,String str2){
        return str1!=null&&str2!=null;
    }
    public static boolean NotNullStringCheck(Integer integer1,Integer integer){
        return integer1!=null&&integer!=null;
    }
    public static boolean NotNullStringCheck(String str,Integer integer2,Integer integer1){
        return !"".equals(str)&&integer1!=null&&integer2!=null;
    }
    public static boolean NotNullStringCheck(Integer integer3,Integer integer2,Integer integer1){
        return integer3!=null&&integer1!=null&&integer2!=null;
    }
    public static boolean NotNullStringCheck(String str1,String str2,Integer integer1,Integer integer2){
        return integer1!=null&&integer2!=null&&!"".equals(str1)&&!"".equals(str2);
    }
    public static boolean NotNullStringCheck(Integer integer1,Integer integer2,Integer integer3,Integer integer4){
        return integer1!=null&&integer2!=null&&integer3!=null&&integer4!=null;
    }
    //参数为空
    public static boolean NullStringCheck(String str){
        return "".equals(str);
    }
    public static boolean NullStringCheck(Integer integer){
        return integer==null;
    }
    public static boolean NullStringCheck(String str,Integer integer){
        return "".equals(str)&&integer==null;
    }
    public static boolean NullStringCheck(String str1,String str2){
        return str1==null&&str2==null;
    }
    public static boolean NullStringCheck(Integer integer1,Integer integer){
        return integer1==null&&integer==null;
    }
    public static boolean NullStringCheck(String str,Integer integer2,Integer integer1){
        return "".equals(str)&&integer1==null&&integer2==null;
    }
    public static boolean NullStringCheck(Integer integer3,Integer integer2,Integer integer1){
        return integer3==null&&integer1==null&&integer2==null;
    }
    public static boolean NullStringCheck(String str1,String str2,Integer integer1,Integer integer2){
        return integer1==null&&integer2==null&&"".equals(str1)&&"".equals(str2);
    }
    public static boolean NullStringCheck(Integer integer1,Integer integer2,Integer integer3,Integer integer4){
        return integer1==null&&integer2==null&&integer3==null&&integer4==null;
    }


    
    
    
    
    
    
    
}
