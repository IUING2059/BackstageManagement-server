package com.backstageManagement.pojo;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;           //姓名模糊查询
    private Integer gender;        //性别筛选
    private Integer clazzId;       //所属班级筛选
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;       //毕业起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;         //毕业结束时间
    private Integer degree; //最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士

    private String clazzName;//班级名称
}