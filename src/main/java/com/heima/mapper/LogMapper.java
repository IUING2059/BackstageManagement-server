package com.heima.mapper;

import com.heima.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    List<OperateLog> findAll();

    /*
    查询总记录数
     */
    long countTotal();
}
