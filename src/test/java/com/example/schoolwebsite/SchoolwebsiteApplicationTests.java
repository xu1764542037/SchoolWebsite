package com.example.schoolwebsite;

import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.entity.Profession;
import com.example.schoolwebsite.entity.UserInfo;
import com.example.schoolwebsite.service.impl.*;
import com.example.schoolwebsite.utils.IdMaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolwebsiteApplicationTests {

    @Autowired
    GradeServiceImpl gradeService;
    @Autowired
    ProfessionServiceImpl professionService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    ClassServiceImpl classService;
    @Autowired
    UserInfoServiceImpl userInfoService;

    @Test
    void contextLoads(){
        UserInfo userInfo = new UserInfo();
        userInfo.setIdCardNumber("336655233339995564");
        userInfo.setPassword("995564");
        System.out.println(userInfoService.select("336655233339995564","995564"));
    }
    @Test
    void tests(){
        System.out.println(IdMaker.BranchIdMaker());
    }
}
