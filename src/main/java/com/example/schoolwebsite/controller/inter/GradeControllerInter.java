package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Grade;

public interface GradeControllerInter {
    BackReturn add(Grade grade);
    BackReturn delete(Integer gradeId);
    BackReturn update(Grade grade);
    BackReturn select(String studentName,String courseName);
}
