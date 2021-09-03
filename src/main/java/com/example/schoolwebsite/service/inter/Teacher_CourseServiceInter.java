package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Teacher_Course;

public interface Teacher_CourseServiceInter {
    BackReturn add(Teacher_Course teacher_course);
    BackReturn delete(Integer teacher_courseId);
    BackReturn update(Teacher_Course teacher_course);
    BackReturn select(String TeacherName,String CourseName);
}
