package com.heima.service;

import com.heima.pojo.Dept;

import java.util.List;

public interface DeptService {

//    查询所有的部门信息
    List<Dept> findAll();

    /* *
     * 根据id删除部门信息
     */
    void deleteById(Integer id);

    /* *
     * 添加部门信息
     */
    void add(Dept dept);

    /*
    根据ID查询部门数据
     */
    Dept getById(Integer id);

    /*
    更新部门信息根据ID
     */
    void updateById(Dept dept);
}
