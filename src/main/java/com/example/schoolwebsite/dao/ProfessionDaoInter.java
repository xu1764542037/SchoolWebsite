package com.example.schoolwebsite.dao;

import com.example.schoolwebsite.entity.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProfessionDaoInter{
    Boolean add(@Param("profession") Profession profession);
    Boolean delete(@Param("professionId") Integer professionId);
    Boolean update(@Param("profession") Profession profession);
    List<Profession> selectbyname(@Param("professionName") String professionName,@Param(value ="branch") Integer branch);
    List<Profession> selectbyid(@Param("professionId") Integer professionId);
}
