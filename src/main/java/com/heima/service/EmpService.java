package com.heima.service;


import com.heima.pojo.Emp;
import com.heima.pojo.EmpQueryParam;
import com.heima.pojo.LoginInfo;
import com.heima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询员工数据
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
    保存员工数据
     */
    void save(Emp emp);

    /*
    批量删除员工数据
     */
    void delete(List<Integer> ids);

    /*
    查询员工数据
     */
    Emp getInfo(Integer id);

    /*
    修改员工数据
     */
    void update(Emp emp);

    /*
    查询全部员工
     */
    List<Emp> getAll();

    /*
    登录
     */
    LoginInfo login(Emp emp);
}
