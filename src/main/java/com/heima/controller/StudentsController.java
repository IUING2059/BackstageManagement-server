package com.heima.controller;


import com.heima.anno.Log;
import com.heima.pojo.*;
import com.heima.service.ClazzsService;
import com.heima.service.EmpLogService;
import com.heima.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentsController {

    @Autowired
    private StudentsService studentsService;
    @Autowired
    private ClazzsService clazzsService;


    /*
    分页查询学生信息
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学生数据，{}", studentQueryParam);
        PageResult<Student> pageResult = studentsService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /*
    根据ID批量删除学员信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("删除学员的ids：{}", ids);
        studentsService.deleteByIds(ids);
        return Result.success();
    }

    /*
    添加新学员
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("新增员工信息：{}", student);
        studentsService.save(student);
        return Result.success();
    }

    /*
    根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员信息：{}", id);
        Student student = studentsService.getInfo(id);
        return Result.success(student);
    }

    /*
    修改学员
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息：{}", student);
        studentsService.update(student);
        return Result.success();
    }

    /*
    学员违纪处理
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result updateScore(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理id：{}，扣除分数：{}", id, score);
        studentsService.updateScore(id,score);
        return Result.success();
    }


}
