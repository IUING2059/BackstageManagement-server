package com.backstageManagement.controller;

import com.backstageManagement.anno.Log;
import com.backstageManagement.pojo.Clazz;
import com.backstageManagement.pojo.ClazzQuery;
import com.backstageManagement.pojo.PageResult;
import com.backstageManagement.pojo.Result;
import com.backstageManagement.service.ClazzsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzsController {

    @Autowired
    private ClazzsService clazzsService;

    /*
    查询所有班级
     */
    @GetMapping("/list")
    public Result listToAll() {
        log.info("查询所有班级信息");
        List<Clazz> clazz = clazzsService.findToAll();
        return Result.success(clazz);
    }

    /*
    查询所有班级信息
     */
    @GetMapping
    public Result list(ClazzQuery query) {
        log.info("班级列表数据的条件分页查询，条件：{}", query);
        PageResult<Clazz> clazzsList = clazzsService.findAll(query);
        return Result.success(clazzsList);
    }



    /*
    删除班级信息
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息：{}", id);
        clazzsService.deleteById(id);
        return Result.success();
    }

    /*
    添加班级信息
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级信息：{}", clazz);
        clazzsService.add(clazz);
        return Result.success();
    }

    /*
    根据ID查询
     */
    @GetMapping("/{id}")
    public Result selete(@PathVariable Integer id) {
        log.info("根据ID查询班级信息：{}", id);
        Clazz clazz = clazzsService.seleteById(id);
        return Result.success(clazz);
    }

    /*
    修改班级信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("班级信息修改：{}", clazz);
        clazzsService.update(clazz);
        return Result.success();
    }


}
