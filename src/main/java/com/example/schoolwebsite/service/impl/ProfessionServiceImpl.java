package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.BranchDaoInter;
import com.example.schoolwebsite.dao.ProfessionDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Profession;
import com.example.schoolwebsite.service.inter.ProfessionServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionServiceInter {

    @Autowired
    private ProfessionDaoInter professionDaoInter;

    @Autowired
    private BranchDaoInter branchDaoInter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn add(Profession profession) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (profession == null) {
            backReturn.setMsg("添加内容为空，添加失败");
            backReturn.setCode(0);
        } else {
            if (profession.getBranch() != null
                    && profession.getBranch().getId() != 0
                    && !"".equals(profession.getProfessionName()))//对传入数据有效性判定
            {
                if (branchDaoInter.selectbyid(profession.getBranch().getId()).size() > 0) {
                    //对分院代码有效性进行判定
                    if (professionDaoInter.selectbyname(profession.getProfessionName(),profession.getBranch().getId()).size() > 0) {
                        backReturn.setMsg("专业已存在，请勿重复添加");
                        backReturn.setCode(0);
                        return backReturn;
                    }
                    profession.setId(IdMaker.ProfessionIdMaker());
                    try{
                        if (professionDaoInter.add(profession)) {
                            backReturn.setMsg("添加成功");
                            backReturn.setCode(1);
                        }
                    }catch(Exception e){
                        throw new Exception();
                    }
                } else {
                    backReturn.setMsg("分院信息无效,添加失败");
                    backReturn.setObj(0);
                }
            } else {
                backReturn.setMsg("分院信息、专业名称均不能为空，添加失败");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn delete(Integer professionId) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (StringTool.NullStringCheck(professionId)){
            backReturn.setMsg("输入的删除条件无效，删除失败");
            backReturn.setCode(0);
        } else {
            if (professionDaoInter.selectbyid(professionId).size() > 0){
                try{
                    if (professionDaoInter.delete(professionId)) {
                        backReturn.setMsg("删除成功");
                        backReturn.setCode(1);
                    }
                }catch (Exception e){
                    throw new Exception();
                }
            } else {
                backReturn.setMsg("数据不存在，删除失败");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BackReturn update(Profession profession) throws Exception {
        BackReturn backReturn = new BackReturn();
        if (professionDaoInter.selectbyid(profession.getId()).size() > 0) {
            try{
                if (professionDaoInter.update(profession)) {
                    backReturn.setMsg("修改成功");
                    backReturn.setCode(1);
                }
            }catch (Exception e){
                throw new Exception();
            }
        } else {
            backReturn.setMsg("该专业不存在，修改失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public BackReturn select(String professionName, Integer branch) {
        BackReturn backReturn = new BackReturn();
        List<Profession> professions;
        if (StringTool.NullStringCheck(professionName, branch)) {
            professions = professionDaoInter.selectbyname(null, null);
            if (professions.size() > 0) {
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(professions);
            } else {
                backReturn.setMsg("系统出错或数据被清空");
                backReturn.setCode(-1);
            }
        } else {
            professions = professionDaoInter.selectbyname(professionName, branch);
            if (professions.size() > 0) {
                backReturn.setMsg("已查询到符合要求的数据");
                backReturn.setCode(1);
                backReturn.setObj(professions);
            } else {
                backReturn.setCode(0);
                backReturn.setMsg("未查询到指定数据");
            }
        }
        return backReturn;
    }
}