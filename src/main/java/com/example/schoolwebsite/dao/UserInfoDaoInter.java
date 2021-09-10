package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserInfoDaoInter {
    Boolean delete(@Param("userInfoId") String userInfoId);
    Boolean update(@Param("userInfo") UserInfo userInfo);
    List<UserInfo> selectbyid(@Param("userInfoId") String userInfoId,@Param("password") String password);
    Boolean checkuser(@Param("userInfoId") String userInfoId);
}
