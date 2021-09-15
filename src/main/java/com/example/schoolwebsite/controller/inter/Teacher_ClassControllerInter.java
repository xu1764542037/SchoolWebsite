package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Class;

import java.util.List;

public interface Teacher_ClassControllerInter {
    BackReturn add(Teacher_Class teacher_class);
    BackReturn BatchAdd(List<Teacher_Class> teacher_classes);
    BackReturn delete(Integer id,Integer TeacherId,Integer ClassId,Integer CourseId);
    BackReturn update(Teacher_Class teacher_class);
    BackReturn select(Integer teacherId,Integer courseId,Integer classId);
}
