package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;

public interface BranchServiceInter {
    BackReturn add(Branch branch) throws Exception;
    BackReturn delete(Integer branchId) throws Exception;
    BackReturn update(Branch branch) throws Exception;
    BackReturn select(String branchName) throws Exception;
}
