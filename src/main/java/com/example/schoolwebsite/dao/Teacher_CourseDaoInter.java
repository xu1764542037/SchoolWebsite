package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Teacher_Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface Teacher_CourseDaoInter{
    int add(@Param("teacher_course")Teacher_Course teacher_course);
    int delete(@Param("teacher_courseId") Integer teacher_courseId);
    int update(@Param("teacher_course") Teacher_Course teacher_course);
    List<Teacher_Course> selectbyid(@Param("teacher_courseId") Integer teacher_courseId);
    List<Teacher_Course> selectbyname(@Param("teacherName") String TeacherName,@Param("courseName") String CourseName);
}
