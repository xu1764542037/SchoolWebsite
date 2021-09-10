package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminDaoInter {
    Boolean select(@Param("login") String Login,@Param("password") String Password);
    Boolean update(Admin admin);
    Boolean add(Admin admin);
    List<Admin> selectall();
}
