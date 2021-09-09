package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StudentDaoInter{
    int add(@Param("student") Student student);
    int update(@Param("student") Student student);
    List<Student> selectbyname(@Param("studentName") String studentName, @Param("branch") Integer branch,@Param("Class")String Class);
    List<Student> selectbyid(@Param("IdCardNumber") String IdCardNumber,@Param("studentId") Integer studentId);
}
