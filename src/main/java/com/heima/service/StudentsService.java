package com.heima.service;

import com.heima.pojo.PageResult;
import com.heima.pojo.Student;
import com.heima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentsService {
    /*
    分页查询学员数据
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /*
    批量删除学员数据
     */
    void deleteByIds(List<Integer> ids);

    /*
    保存学员数据
     */
    void save(Student student);

    /*
    查询学员数据
     */
    Student getInfo(Integer id);

    /*
    修改学员数据
     */
    void update(Student student);

    /*
    修改违纪学员分数
     */
    void updateScore(Integer id, Integer score);
}
