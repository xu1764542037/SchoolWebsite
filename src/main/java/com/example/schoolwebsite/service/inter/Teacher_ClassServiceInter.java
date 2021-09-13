package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Class;

import java.util.List;

public interface Teacher_ClassServiceInter {
    BackReturn add(Teacher_Class teacher_class);
    BackReturn BatchAdd(List<Teacher_Class> teacher_classes);
    BackReturn delete(Teacher_Class teacher_class);
    BackReturn update(Teacher_Class teacher_class);
    BackReturn select(String teacherId,String courseId,String classId);
}
