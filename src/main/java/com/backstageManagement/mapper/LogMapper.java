package com.backstageManagement.mapper;

import com.backstageManagement.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {
    List<OperateLog> findAll(@Param("start") int start, @Param("pageSize") Integer pageSize);

    /*
    查询总记录数
     */
    long countTotal();
}
