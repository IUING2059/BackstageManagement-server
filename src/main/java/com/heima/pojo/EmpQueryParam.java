package com.heima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page=1;  //页码
    private Integer pageSize=10;  //每页条数
    private String name;  // 姓名
    private Integer gender;  // 性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;  // 入职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;  // 离职时间
}
