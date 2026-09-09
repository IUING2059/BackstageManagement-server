package com.backstageManagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backstageManagement.mapper.EmpExprMapper;
import com.backstageManagement.mapper.EmpLogMapper;
import com.backstageManagement.mapper.EmpMapper;
import com.backstageManagement.pojo.*;
import com.backstageManagement.service.EmpLogService;
import com.backstageManagement.service.EmpService;
import com.backstageManagement.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private EmpLogMapper empLogMapper;


    //    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//        //2.调用mapper接口，查询分页数据
//        Integer start=(page-1)*pageSize;
//        List<Emp> rows=empMapper.list();
//        //3.封装PageResult对象并返回
//        return new PageResult<Emp>(total,rows);
//    }
    /*
    基于pageHelper分页插件
    分页查询
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.调用mapper查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析结果，返回结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    /*
    保存员工数据
     */
    @Transactional//事物管理
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //2.保存员工的工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合，为empID赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工信息：" + emp);
            empLogMapper.insert(empLog);
        }


    }

    /*
    删除员工数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /*
    查询员工信息
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /*
    修改员工数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.根据ID修改员工工作经历信息
        //2.1先根据员工ID删除
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
            //2.2再添加

            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);

        }

    }

    /*
    查询全部员工
     */
    @Override
    public List<Emp> getAll() {
        return empMapper.getAll();
    }

    /*
    登录
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，查询员工用户名或者密码
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2.判断是否存在这个员工，如果存在，要组装信息
        if (e != null) {
            log.info("登陆成功，员工信息，service层{}", e);
            //生成JWT令牌
            Map<String, Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3.不存在，则返回null
        return null;
    }
    //更新密码
    @Override
    public void updateById(Emp empDb) {
        empMapper.updateById(empDb);
    }
}
