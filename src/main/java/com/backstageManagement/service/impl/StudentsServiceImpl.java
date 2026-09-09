package com.backstageManagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backstageManagement.mapper.StudentsMapper;
import com.backstageManagement.pojo.PageResult;
import com.backstageManagement.pojo.Student;
import com.backstageManagement.pojo.StudentQueryParam;
import com.backstageManagement.service.ClazzsService;
import com.backstageManagement.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    // 注入mapper，不是service
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private ClazzsService clazzsService;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.分页开启
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2.mapper查询学生
        List<Student> stuList = studentsMapper.list(studentQueryParam);
        //3.强转Page
        Page<Student> p = (Page<Student>) stuList;
        //4.返回分页结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /*
    批量删除学员数据
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        studentsMapper.deleteBy(ids);

    }

    /*
    保存学员数据
     */
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.insert(student);
    }

    /*
    查询学员数据根据ID
     */
    @Override
    public Student getInfo(Integer id) {
        return studentsMapper.getById(id);
    }

    /*
    修改学员数据
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.update(student);

    }

    /*
    修改违纪学员分数
     */

    @Override
    public void updateScore(Integer id, Integer score) {
        // 1.基础参数合法性校验
        if (id == null) {
            throw new RuntimeException("学生ID不能为空");
        }
        if (score == null || score <= 0 || score > 50) {
            throw new RuntimeException("扣分数值必须介于 1~50 之间");
        }

        // 2.执行数据库更新：次数+1、累计扣分累加
        int affectRows = studentsMapper.updateViolationInfo(id, score);

        // 3.判断是否更新成功（影响行数为0代表id不存在）
        if (affectRows <= 0) {
            throw new RuntimeException("该学生不存在，违纪扣分记录添加失败");
        }
    }
}