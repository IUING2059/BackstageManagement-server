package com.backstageManagement.controller;

import com.backstageManagement.anno.Log;
import com.backstageManagement.pojo.Dept;
import com.backstageManagement.pojo.Result;
import com.backstageManagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);//固定

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)//method:指定请求方式
    /*查询全部部门信息*/
    @GetMapping
    public Result list() {
        //System.out.println("查询全部的部门信息");
        log.info("查询全部的部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门信息
    @Log
    @DeleteMapping
    public Result delete(Integer id) {
        //System.out.println("删除部门信息" + id);
        log.info("删除部门信息: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*添加部门信息*/
    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门信息: {}", dept);
        deptService.add(dept);

        // 获取数据库回填后的主键id
        Integer currentId = dept.getId();
        // 安全处理，避免null调用intValue()
        if (currentId == null) {
            throw new RuntimeException("新增部门失败，未获取到主键ID");
        }
        // 这里再使用，不会空指针
        int id = currentId.intValue();
        log.info("新增部门id：{}", id);

        return Result.success();
    }


    /*
    根据id查询部门回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        //System.out.println("根据Id查询部门信息 ： " + id);
        log.info("根据Id查询部门信息: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
    修改部门信息根据id
     */
    @Log
    @PutMapping()
    public Result updateById(@RequestBody Dept dept) {
        //System.out.println("修改部门信息： " + dept);
        log.info("修改部门信息: {}", dept);
        deptService.updateById(dept);
        return Result.success();
    }
}
