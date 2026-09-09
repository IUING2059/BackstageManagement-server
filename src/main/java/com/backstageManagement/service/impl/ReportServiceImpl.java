package com.backstageManagement.service.impl;

import com.backstageManagement.mapper.EmpMapper;
import com.backstageManagement.mapper.StudentsMapper;
import com.backstageManagement.pojo.ClazzsOption;
import com.backstageManagement.pojo.JobOption;
import com.backstageManagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentsMapper studentsMapper;

    /*
    统计员工信息以及员工工作信息
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用map接口
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2.组装数据并返回结果
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("position")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    /*
    统计员工性别信息
     */
    @Override
    public List<Map<String, Objects>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /*
    学员学历统计
     */
    @Override
    public List<Map<String, Objects>> getStudentGreeDate() {
        return studentsMapper.getStudentDegreeData();
    }

    /*
    班级人数统计
     */
    @Override
    public ClazzsOption getStudentCountData() {
        //1.调用map接口
        List<Map<String, Object>> list =studentsMapper.countStudentCountData();
        //2.组装数据并返回结果
        List<Object>clazzList=list.stream().map(dataMap->dataMap.get("c")).toList();
        List<Object>dataList=list.stream().map(dataMap->dataMap.get("num")).toList();
        return new ClazzsOption(clazzList,dataList);
    }
}
