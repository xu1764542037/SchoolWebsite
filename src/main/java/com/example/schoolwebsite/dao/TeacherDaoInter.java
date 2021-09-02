package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeacherDaoInter{
    int add(@Param("teacher") Teacher teacher);
    int delete(@Param("teacherId") Integer teacherId);
    int update(@Param("teacher") Teacher teacher);
    List<Class> selectbyname(@Param("teacherName") String className, @Param("profession") Integer profession);
    List<Class> selectbyid(@Param("Id") Integer classId);
    List<Class> select();
}
