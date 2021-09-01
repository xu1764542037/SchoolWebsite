package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProfessionDaoInter{
    int add(@Param("profession") Profession profession);
    int delete(@Param("professionId") Integer professionId);
    int update(@Param("profession") Profession profession);
    List<Profession> selectbyname(@Param("professionName") String professionName,@Param(value ="branch") Integer branch);
    List<Profession> selectbyid(@Param("professionId") Integer professionId);
    List<Profession> select();
}
