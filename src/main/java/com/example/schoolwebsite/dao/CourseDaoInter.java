package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CourseDaoInter{
    Boolean add(@Param("course") Course course);
    Boolean delete(@Param("courseId") Integer courseId);
    Boolean update(@Param("course") Course course);
    List<Course> selectbyname(@Param("courseName") String courseName);
    List<Course> selectbyid(@Param("courseId") Integer courseId);
    List<Course> select();
}
