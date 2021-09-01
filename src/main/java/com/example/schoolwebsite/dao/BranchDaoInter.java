package com.example.schoolwebsite.dao;


import com.example.schoolwebsite.entity.Branch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BranchDaoInter{
    int add(Branch branch);
    int delete(Integer branchId);
    int Update(Branch branch);
    List<Branch> selectbyname(String branchName);
    List<Branch> selectbyid(Integer branchId);
    List<Branch> select();
}
