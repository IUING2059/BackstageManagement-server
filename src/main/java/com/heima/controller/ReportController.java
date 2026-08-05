package com.heima.controller;

import com.heima.pojo.ClazzsOption;
import com.heima.pojo.JobOption;
import com.heima.pojo.Result;
import com.heima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /*
    统计员工信息以及员工工作信息
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
    统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别信息");
        List<Map<String, Objects>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /*
    学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String,Objects>> degreeData=reportService.getStudentGreeDate();
        return Result.success(degreeData);

    }
    /*
    班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("班级人数统计");
        ClazzsOption clazzsOption=reportService.getStudentCountData();
        return Result.success(clazzsOption);
    }

}
