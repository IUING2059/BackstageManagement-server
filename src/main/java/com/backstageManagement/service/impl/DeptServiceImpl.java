package com.backstageManagement.service.impl;

import com.backstageManagement.mapper.DeptMapper;
import com.backstageManagement.mapper.EmpMapper;
import com.backstageManagement.pojo.Dept;
import com.backstageManagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    /**
     * 查询所有部门信息
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据id删除部门信息
     */
    @Override
    public void deleteById(Integer id) {
        //1.查询当前部门下员工数量
        Integer empCount = empMapper.countByDeptId(id);
        //2.员工数>0，抛出运行时异常
        if(empCount > 0){
            throw new RuntimeException("对不起，当前部门下有员工，不能直接删除！");
        }
        //3.无员工，正常删除部门
        deptMapper.deleteById(id);
    }

    /**
     * 添加部门信息
     */
    @Override
    public void add(Dept dept) {
        //1.补全基础属性 -creatTime，updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口插入数据
        deptMapper.insert(dept);
    }

    /*
    根据ID查询部门信息
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /*
    更新部门信息根据ID
     */
    @Override
    public void updateById(Dept dept) {
        //1.补全基础属性 -updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口更新数据
        deptMapper.updateById(dept);
    }


}
