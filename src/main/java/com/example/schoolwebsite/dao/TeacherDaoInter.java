package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TeacherDaoInter{
    int add(@Param("teacher") Teacher teacher);
    int update(@Param("teacher") Teacher teacher);
    List<Teacher> selectbyname(@Param("teacherName") String teacherName, @Param("branch") Integer branch);
    List<Teacher> selectbyid(@Param("IdCardNumber") String IdCardNumber,@Param("teacherId") Integer teacherId);
}
