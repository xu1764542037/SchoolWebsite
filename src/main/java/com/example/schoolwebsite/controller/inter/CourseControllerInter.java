package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Course;

public interface CourseControllerInter {
    BackReturn add(Course course);
    BackReturn delete(Integer courseId);
    BackReturn update(Course course);
    BackReturn select(String courseName);
}
