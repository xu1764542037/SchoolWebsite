package com.example.schoolwebsite.service.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;

public interface BranchServiceInter {
    BackReturn add(Branch branch);
    BackReturn delete(Integer branchId);
    BackReturn update(Branch branch);
    BackReturn select(String branchName);
}
