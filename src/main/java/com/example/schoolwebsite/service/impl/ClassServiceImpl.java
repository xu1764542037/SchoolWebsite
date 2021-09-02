package com.example.schoolwebsite.service.impl;

import com.example.schoolwebsite.dao.ClassDaoInter;
import com.example.schoolwebsite.dao.ProfessionDaoInter;
import com.example.schoolwebsite.entity.BackReturn;
import com.example.schoolwebsite.entity.Class;
import com.example.schoolwebsite.service.inter.ClassServiceInter;
import com.example.schoolwebsite.utils.IdMaker;
import com.example.schoolwebsite.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassServiceInter {

    @Autowired
    private ClassDaoInter classDaoInter;
    @Autowired
    private ProfessionDaoInter professionDaoInter;

    @Override
    public BackReturn add(Class classes){
        BackReturn backReturn = new BackReturn();
        Date date = new Date();
        if (classes!=null) {
            if (!"".equals(classes.getClassName())) {
                if (classes.getProfession().getId()!=null&&classes.getProfession().getId()!=0){//专业ID判空
                    if (professionDaoInter.selectbyid(classes.getProfession().getId()).size()>0) {//专业ID有效性
                        if (classDaoInter.selectbyname(classes.getClassName(),classes.getProfession().getId()).size()>0) {//班级查重
                            backReturn.setMsg("班级已存在，请勿重复添加");
                            backReturn.setCode(0);
                            return backReturn;
                        }
                        classes.setId(IdMaker.ClassIdMaker(classes.getProfession().getId()));//给出班级ID
                        if (CheckClassName(classes.getClassName()).getCode()==1){
                            classes.setClassName(CheckClassName(classes.getClassName()).getMsg());
                        }else{
                            backReturn.setMsg(CheckClassName(classes.getClassName()).getMsg());
                            backReturn.setCode(0);
                            return backReturn;
                        }
                        if (classDaoInter.add(classes)>=0) {
                            backReturn.setMsg("添加成功");
                            backReturn.setCode(1);
                        }else{
                            backReturn.setMsg("系统异常，添加失败");
                            backReturn.setCode(-1);
                        }
                    }else{
                        backReturn.setMsg("专业名称无效，添加失败");
                        backReturn.setCode(0);
                    }
                }else{
                    backReturn.setMsg("专业名称不能为空");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("班级名称不能为空");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("数据为空，添加失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn delete(Integer classId){
        BackReturn backReturn = new BackReturn();
        if (classId!=null&&classId!=0){
            if (classDaoInter.selectbyid(classId).size()>0){
                if (classDaoInter.delete(classId)>=0) {
                    backReturn.setMsg("删除成功");
                    backReturn.setCode(1);
                }else{
                    backReturn.setMsg("系统异常，删除失败");
                    backReturn.setCode(-1);
                }
            }else{
                backReturn.setMsg("数据不存在或已被清除");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("删除条件无效");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn update(Class classes){
        BackReturn backReturn = new BackReturn();
        if (classes!=null){
            if (classes.getId()!=null){
                if (classDaoInter.selectbyid(classes.getId()).size()>0){
                    if (classes.getClassName()!=null&&!"".equals(classes.getClassName())){
                        classes.setClassName(CheckClassName(classes.getClassName()).getMsg());
                    }
                    if (classDaoInter.update(classes)>=0) {
                        backReturn.setMsg("修改成功");
                        backReturn.setCode(1);
                    }else{
                        backReturn.setMsg("系统异常。修改失败");
                        backReturn.setCode(-1);
                    }
                }else{
                    backReturn.setMsg("数据不存在，无法修改");
                    backReturn.setCode(0);
                }
            }else{
                backReturn.setMsg("传入数据不足，无法修改");
                backReturn.setCode(0);
            }
        }else{
            backReturn.setMsg("传入数据无效，修改失败");
            backReturn.setCode(0);
        }
        return backReturn;
    }

    @Override
    public BackReturn select(String className){
        BackReturn backReturn = new BackReturn();
        List<Class> classes;
        if (!"".equals(className)){
            classes = classDaoInter.selectbyname(className,null);
            if (classes.size()>0) {
                backReturn.setMsg("已查询到指定数据");
                backReturn.setCode(1);
                backReturn.setObj(classes);
            }else{
                backReturn.setMsg("未查询到指定数据");
                backReturn.setCode(0);
            }
        }else {
            classes = classDaoInter.select();
            if (classes.size() > 0) {
                backReturn.setMsg("已查询到数据");
                backReturn.setCode(1);
                backReturn.setObj(classes);
            } else {
                backReturn.setMsg("系统异常或数据被清除，查询失败");
                backReturn.setObj(-1);
            }
        }
        return backReturn;
    }

    @Override
    //班级名称判定及处理
    public BackReturn CheckClassName(String classes) {
        BackReturn backReturn = new BackReturn();
        Date date = new Date();
        String str1;
        backReturn.setMsg(classes);
        if (StringTool.StringToNumberString(classes.substring(0,5)).length()!=4&&StringTool.StringToNumberString(classes.substring(0,5)).length()<4){//判断班级年份是否完整
            if(StringTool.StringToNumberString(classes.substring(0,5)).length()==2){//补充20在开头
                str1=("20"+classes);
                backReturn.setMsg(str1);
                backReturn.setCode(1);
            }else{
                str1=(date.toString().substring(date.toString().length()-4)+classes);//无数字或1、3位则直接写当前年份
                backReturn.setMsg(str1);
                backReturn.setCode(1);
            }
        }else{
            if (StringTool.StringToNumberString(classes.substring(0,5)).length()>4){
                backReturn.setMsg("班级名称无效，无法添加");
                backReturn.setCode(0);
            }
        }
        return backReturn;
    }
}
