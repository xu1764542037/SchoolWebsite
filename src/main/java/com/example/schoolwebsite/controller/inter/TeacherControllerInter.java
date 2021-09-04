package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher;

public interface TeacherControllerInter {
    BackReturn add(Teacher teacher);
    BackReturn delete(Integer teacherId,String IdCardNumber);
    BackReturn update(Teacher teacher);
    BackReturn select(String teachername,Integer branch);
}
