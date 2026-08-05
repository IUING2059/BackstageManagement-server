package com.heima.service;

import com.heima.pojo.OperateLog;
import com.heima.pojo.PageResult;

public interface LogService {
    /*
    查询所有日志
     */
    PageResult<OperateLog> findAll();
}
