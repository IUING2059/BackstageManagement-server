package com.heima.mapper;

import com.heima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门信息
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    /**
     * 根据id删除部门信息
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加部门信息
     */
    /**
     * 添加部门信息
     */
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Dept dept);

    /*
    根据ID查询部门信息
     */
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    /**
     * 根据ID修改部门信息
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateById(Dept dept);
}
