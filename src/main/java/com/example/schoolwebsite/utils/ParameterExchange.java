package com.example.schoolwebsite.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParameterExchange {
    /*将JSON数据转换成JavaBean对象*/
    public static <T> T JSONObjectToJavaBean(JSONObject source//传入的json数据
            , Class<T> javaBean)
    {
        if (source==null) return null;
        Method[] beanMethods = javaBean.getMethods();//获取指定对象所有的JavaBean方法
        T tempBean = null;
        try {
            tempBean = javaBean.newInstance();//尝试以该类新建对象
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        for (Method method : beanMethods)//遍历被创建对象中的所有方法
        {
            String field = method.getName();
            if (!field.contains("set")) continue;//没有get方法的过

            String oldField=field.substring(3); //获取方法名并存储，用于JSON数组
            field = field.substring(3); //获取方法名并存储，用于单个JSON

            field = field.substring(0,1).toLowerCase() + field.substring(1); //取方法名的第一个字母为小写，后面的不变
            if (!source.has(field))
                continue;
            else {
                try {
                    if (source.get(field) instanceof JSONObject)//若获取的值仍然为Json对象则继续调用本方法解封，直到内容值不为JSONObject为止
                    {
                        method.invoke(tempBean, JSONObjectToJavaBean(source.getJSONObject(field), method.getParameterTypes()[0]));//invoke执行被递归的函数

                    } else if (source.get(field) instanceof JSONArray)//若取值为JSON数组
                    {
                        List<Object> lTemp=new ArrayList<Object>();//新建数组
                        JSONArray jArray=source.getJSONArray(field);//获取JSON数组（值）
                        for(int i=0;i<jArray.length();i++)//数组长度作为判断依据
                        {
                            ParameterizedType pt=null;//定义方法参数变量
                            try {
                                pt = (ParameterizedType)tempBean.getClass().getMethod("get"+oldField).getGenericReturnType();//获取方法返回值类型作为方法参数类型
                            } catch (SecurityException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            lTemp.add(JSONObjectToJavaBean(jArray.getJSONObject(i)//JSON数组元素
                                    ,(Class)pt.getActualTypeArguments()[0]));//？
                        }
                        method.invoke(tempBean, lTemp);//传入数组，执行数组中的方法
                    }
                    else {//若不为JSONObject或者JSONArray则向下匹配参数类型
                        if (source.get(field)==null) continue;
                        if (method.getGenericParameterTypes()[0].toString().equals("class java.util.Date"))//处理日期格式
                        //被执行类型为
                        {
                            method.invoke(tempBean, GetDate(source.get(field).toString()));
                        }else  if (method.getGenericParameterTypes()[0].toString().equals("int"))//数字格式
                        {
                            method.invoke(tempBean, Integer.parseInt(source.get(field).toString()));
                        } else if (method.getGenericParameterTypes()[0].toString().equals("float"))//单精度格式
                        {
                            method.invoke(tempBean, Float.parseFloat(source.get(field).toString()));
                        }
                        else
                            method.invoke(tempBean, source.get(field));
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    System.out.println("异常属性【"+field+"】"+method.getGenericParameterTypes()[0].toString());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("异常JSON属性【"+field+"】");
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return tempBean;
    }
    public static <T> T MapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
    /*将JSON数据转换成JavaBean对象*/
    public static  <T> T MapToJavaBean(Map<String, Object> map, Class<T> javaBean)
    {
        if (map==null) return null;
        Method[] beanMethods = javaBean.getMethods();//获取指定对象所有的JavaBean方法
        T tempBean = null;
        try {
            tempBean = javaBean.newInstance();//新建对象
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        for (Method method : beanMethods)
        {
            String field = method.getName();
            if (field.indexOf("set")<0) continue;//没有get方法的过

            String oldField=field.substring(3);
            field = field.substring(3);
            if (map.containsKey(field)==false)
                continue;
            else {
                try {
                    if (map.get(field) instanceof Map)
                    {
                        method.invoke(tempBean, MapToJavaBean((Map<String,Object>)map.get(field), method.getParameterTypes()[0]));

                    }
                    else {
                        if (map.get(field)==null) continue;
                        if (method.getGenericParameterTypes()[0].toString().equals("class java.util.Date"))//处理日期格式
                        {
                            method.invoke(tempBean, GetDate(map.get(field)));
                        }else  if (method.getGenericParameterTypes()[0].toString().equals("int"))
                        {
                            method.invoke(tempBean, new Object[] {Integer.parseInt(map.get(field).toString())});
                        } else if (method.getGenericParameterTypes()[0].toString().equals("float"))
                        {
                            method.invoke(tempBean, new Object[] {Float.parseFloat(map.get(field).toString())});
                        }else if (method.getGenericParameterTypes()[0].toString().equals("boolean") || method.getGenericParameterTypes()[0].toString().indexOf("Boolean")>=0)
                        {
                            method.invoke(tempBean, new Object[] {Boolean.parseBoolean(map.get(field).toString())});
                        }else if (method.getGenericParameterTypes()[0].toString().equals("BigDecimal") || method.getGenericParameterTypes()[0].toString().indexOf("BigDecimal")>=0)
                        {
                            method.invoke(tempBean, new Object[] {BigDecimal.valueOf(Float.parseFloat(map.get(field).toString()))});
                        }
                        else if (method.getGenericParameterTypes()[0].toString().equals("char"))
                        {
                            method.invoke(tempBean, new Object[] {map.get(field).toString().length()>0?map.get(field).toString().charAt(0):null});
                        }
                        else
                            method.invoke(tempBean, new Object[] {map.get(field)});
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    System.out.println("异常属性【"+field+"】"+method.getGenericParameterTypes()[0].toString());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return tempBean;
    }
    //日期处理函数
    public static Date GetDate(Object value) {
        Date time = (Date)value;
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }
}
