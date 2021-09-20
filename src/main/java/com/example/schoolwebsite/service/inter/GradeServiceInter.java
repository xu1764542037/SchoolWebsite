package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Grade;

public interface GradeServiceInter {
    BackReturn add(Grade grade) throws Exception;
    BackReturn delete(Integer gradeId) throws Exception;
    BackReturn update(Grade grade) throws Exception;
    BackReturn select(String studentName,Integer studentId,String courseName);
}
