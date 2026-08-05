package com.heima.controller;


import com.heima.anno.Log;
import com.heima.pojo.Emp;
import com.heima.pojo.EmpQueryParam;
import com.heima.pojo.PageResult;
import com.heima.pojo.Result;
import com.heima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/*
员工管理Controller
 */
@RequestMapping("/emps")
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工数据
     *
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询员工数据，参数：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
    保存员工数据
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*
    删除员工数据--基于数组
     */
//    @DeleteMapping
//    public Result delete(Integer[] ids) {
//        log.info("删除员工Ids：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    /*
    删除员工数据--基于List
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工Ids：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
    查询员工数据
     */
    @GetMapping("/{id}")
    public Result getId(@PathVariable Integer id) {
        log.info("根据ID查询员工信息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
    修改员工数据
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("员工信息修改：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /*
    查询全部员工
     */
    @GetMapping("/list")
    public Result getAll(){
        log.info("查询全部员工信息");
        List<Emp> emp=empService.getAll();
        return Result.success(emp);
    }

}
