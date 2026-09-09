package com.backstageManagement.service;

import com.backstageManagement.pojo.OperateLog;
import com.backstageManagement.pojo.PageResult;

public interface LogService {
    /*
    查询所有日志
     */
    PageResult<OperateLog> findAll(Integer page,Integer pageSize);
}
