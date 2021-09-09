package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Branch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BranchDaoInter{
    Boolean add(@Param("branch") Branch branch);
    Boolean delete(@Param("branchId") Integer branchId);
    Boolean Update(@Param("branch") Branch branch);
    List<Branch> selectbyname(@Param("branchName") String branchName);
    List<Branch> selectbyid(@Param("branchId") Integer branchId);
    List<Branch> select();
}
