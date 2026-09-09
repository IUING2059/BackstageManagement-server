package com.backstageManagement.service.impl;

import com.backstageManagement.mapper.LogMapper;
import com.backstageManagement.pojo.OperateLog;
import com.backstageManagement.pojo.PageResult;
import com.backstageManagement.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    /*
    查询所有日志
     */
    @Override
    public PageResult<OperateLog> findAll(Integer page,Integer pageSize) {
        List<OperateLog> rows = logMapper.findAll(page,pageSize);
        long total=logMapper.countTotal();
        return new PageResult<>(total,rows);
    }
}
