package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Branch;
import com.example.schoolwebsite.service.inter.BranchServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchServiceInter {

    @Autowired
    private BranchDaoInter branchDaoInter;

    @Override
    public BackReturn add(Branch branch) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (branch==null){
            backReturn.setCode(0);
            backReturn.setMsg("添加信息为空，无法添加");
        }else{
            if (branchDaoInter.selectbyname(branch.getBranchName()).size()>0){
                backReturn.setCode(0);
                backReturn.setMsg("该学院名称已存在，请勿重复添加");
                return backReturn;
            }
            if (!"".equals(branch.getBranchName())){
                branch.setId(IdMaker.BranchIdMaker());
                if (branchDaoInter.add(branch)>=0){
                    backReturn.setMsg("添加成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setCode(-1);
                    backReturn.setMsg("系统异常，添加失败");
                }
            }else {
                backReturn.setMsg("学院名称不能为空");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(Integer branchId) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (branchId==null){
            backReturn.setCode(0);
            backReturn.setMsg("无效的删除条件");
        }else{
            if (branchDaoInter.selectbyid(branchId).size()==0){
                backReturn.setMsg("数据不存在，无法删除");
                backReturn.setCode(0);
                return backReturn;
            }
            if (branchDaoInter.delete(branchId)>=0) {
                backReturn.setMsg("删除成功");
                backReturn.setCode(1);
            }else {
                backReturn.setCode(-1);
                backReturn.setMsg("系统异常，删除失败");
            }
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Branch branch) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (branchDaoInter.selectbyid(branch.getId()).size()>0) {
            if ("".equals(branch.getBranchName())) {
                backReturn.setMsg("分院名称不能为空");
                backReturn.setCode(0);
                return backReturn;
            }else {
                if (branchDaoInter.Update(branch)>=1) {
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，修改失败");
                    backReturn.setCode(-1);
                }
                return backReturn;
            }
        }else{
            branch.setId(IdMaker.BranchIdMaker());
            if (branchDaoInter.add(branch)>=0){
                backReturn.setMsg("该分院不存在，已为您添加分院数据");
                backReturn.setCode(0);
            }else{
                backReturn.setMsg("分院不存在，系统出错");
                backReturn.setCode(-1);
            }
            return backReturn;
        }
    }

    @Override
    public BackReturn select(String branchName) throws Exception
    {
        BackReturn backReturn = new BackReturn();
        List<Branch> branches;
        if ("".equals(branchName)) {
            branches = branchDaoInter.select();
            if (branches.size()>0){
                backReturn.setObj(branches);
                backReturn.setMsg("查询成功");
                backReturn.setCode(1);
            }else {
                backReturn.setMsg("系统出错或数据已被清空，未查询到数据");
                backReturn.setCode(-1);
            }
        }else{
            branches = branchDaoInter.selectbyname(branchName);
            if (branches.size()>0){
                backReturn.setObj(branches);
                backReturn.setMsg("已查询到符合条件的数据");
                backReturn.setCode(1);
            }else {
                backReturn.setMsg("系统出错或数据已被删除，未查询到指定数据");
                backReturn.setCode(-1);
            }
        }
        return backReturn;
    }
}
