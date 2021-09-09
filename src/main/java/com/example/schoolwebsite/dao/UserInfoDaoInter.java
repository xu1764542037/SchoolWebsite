package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserInfoDaoInter {
    int add(@Param("userInfo") UserInfo userInfo);
    int delete(@Param("userInfoId") String userInfoId);
    int update(@Param("userInfo") UserInfo userInfo);
    List<UserInfo> selectbyid(@Param("userInfoId") String userInfoId,@Param("password") String password);
}
