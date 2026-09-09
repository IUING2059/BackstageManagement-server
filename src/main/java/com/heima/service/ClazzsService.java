package com.heima.service;


import com.heima.pojo.Clazz;
import com.heima.pojo.ClazzQuery;
import com.heima.pojo.PageResult;

import java.util.List;

public interface ClazzsService {
    /*
    该接口用于班级列表数据的条件分页查询
     */
    PageResult<Clazz> findAll(ClazzQuery query);



    /*
    删除班级信息
     */
    void deleteById(Integer id);

    /*
    添加班级信息
     */
    void add(Clazz clazz);

    /*
    根据ID查询
     */
    Clazz seleteById(Integer id);

    /*
    修改班级信息
     */
    void update(Clazz clazz);

    /*
    查询所有班级
     */
    List<Clazz> findToAll();
}
