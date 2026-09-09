package com.heima.service.impl;

import com.heima.mapper.LogMapper;
import com.heima.pojo.OperateLog;
import com.heima.pojo.PageResult;
import com.heima.service.LogService;
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
    public PageResult<OperateLog> findAll() {
        List<OperateLog> rows = logMapper.findAll();
        long total=logMapper.countTotal();
        return new PageResult<>(total,rows);
    }
}
