package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.entity.Course;

public interface CourseServiceInter {
    BackReturn add(Course course);
    BackReturn delete(Integer courseId);
    BackReturn update(Course course);
    BackReturn select(String courseName);
}
