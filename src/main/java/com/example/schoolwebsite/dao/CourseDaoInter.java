package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CourseDaoInter{
    int add(@Param("course") Course course);
    int delete(@Param("courseId") Integer courseId);
    int update(@Param("course") Course course);
    List<Course> selectbyname(@Param("courseName") String courseName);
    List<Course> selectbyid(@Param("courseId") Integer courseId);
    List<Course> select();
}
