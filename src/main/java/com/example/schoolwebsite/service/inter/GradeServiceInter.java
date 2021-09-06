package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Grade;

public interface GradeServiceInter {
    BackReturn add(Grade grade);
    BackReturn delete(Integer gradeId);
    BackReturn update(Grade grade);
    BackReturn select(String studentName,String courseName);
}
