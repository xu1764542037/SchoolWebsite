package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.entity.Course;

public interface CourseServiceInter {
    BackReturn add(Course course) throws Exception;
    BackReturn delete(Integer courseId) throws Exception;
    BackReturn update(Course course) throws Exception;
    BackReturn select(String courseName);
}
