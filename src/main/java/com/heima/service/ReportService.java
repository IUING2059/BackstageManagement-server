package com.heima.service;

import com.heima.pojo.ClazzsOption;
import com.heima.pojo.JobOption;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ReportService {
    /*
    统计员工信息以及员工工作信息
     */
    JobOption getEmpJobData();

    /*
    统计员工性别信息
     */
    List<Map<String, Objects>> getEmpGenderData();

    /*
    学员学历统计
     */
    List<Map<String, Objects>> getStudentGreeDate();

    /*
    学员统计
     */
    ClazzsOption getStudentCountData();
}
