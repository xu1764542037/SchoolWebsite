package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GradeDaoInter{
    int add(@Param("grade") Grade grade);
    int delete(@Param("gradeId") Integer gradeId);
    int update(@Param("grade") Grade grade);
    List<Grade> selectbyname(@Param("studentName") String studentName, @Param("courseName") String courseName);
    List<Grade> selectbyid(@Param("gradeId") Integer gradeId);
}
