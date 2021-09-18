package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Class;

import java.util.List;

public interface Teacher_ClassServiceInter {
    BackReturn add(Teacher_Class teacher_class) throws Exception;
    BackReturn BatchAdd(List<Teacher_Class> teacher_classes) throws Exception;
    BackReturn delete(Integer id,Integer TeacherId,Integer ClassId,Integer CourseId) throws Exception;
    BackReturn update(Teacher_Class teacher_class) throws Exception;
    BackReturn select(Integer teacherId,Integer courseId,Integer classId);
}
