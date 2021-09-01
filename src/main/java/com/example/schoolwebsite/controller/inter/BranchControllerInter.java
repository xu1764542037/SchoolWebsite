package com.example.schoolwebsite.controller.inter;

import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;

public interface BranchControllerInter {
    BackReturn add(Branch branch);
    BackReturn delete(Integer branchId);
    BackReturn update(Branch branch);
    BackReturn select(String branchName);
}
