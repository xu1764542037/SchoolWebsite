package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ClassDaoInter{
    Boolean add(@Param("classes") Class classes);
    Boolean delete(@Param("classId") Integer classId);
    Boolean update(@Param("classes") Class classes);
    List<Class> selectbyname(@Param("className") String className,@Param("profession") Integer profession);
    List<Class> selectbyid(@Param("classId") Integer classId);
    List<Class> select();
}
