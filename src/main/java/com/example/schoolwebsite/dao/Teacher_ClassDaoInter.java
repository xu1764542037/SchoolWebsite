package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.Teacher_Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface Teacher_ClassDaoInter {
    Boolean add(Teacher_Class teacher_class);
    Boolean Batchadd(@Param("teacher_class") List<Teacher_Class> teacher_class);
    Boolean delete(@Param("teacher_class") Teacher_Class teacher_class);
    Boolean update(@Param("teacher_class") Teacher_Class teacher_class);
    List<Teacher_Class> select(@Param("teacherId") String teacherId,@Param("courseId") String courseId,@Param("classId") String classId);
}
