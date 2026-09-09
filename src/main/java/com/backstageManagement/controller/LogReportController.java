package com.backstageManagement.controller;

import com.backstageManagement.pojo.OperateLog;
import com.backstageManagement.pojo.PageResult;
import com.backstageManagement.pojo.Result;
import com.backstageManagement.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogReportController {

    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result LogPage(@RequestParam Integer page,
                          @RequestParam Integer pageSize){
        log.info("分页查询日志数据，参数：page={},pageSize={}",page,pageSize);
        PageResult<OperateLog> LogList=logService.findAll(page,pageSize);
        return Result.success(LogList);

    }
}
