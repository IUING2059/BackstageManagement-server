package com.backstageManagement.mapper;

import com.backstageManagement.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzsMapper {

    /*
    该接口用于班级列表数据的条件分页查询
     */
    List<Clazz> findAll(
            @Param("name") String name,
            @Param("begin") String begin,
            @Param("end") String end,
            @Param("start") Integer start,
            @Param("pageSize") Integer pageSize
    );

    /*
    查询总班级数量
     */
    Long countTotal(
            @Param("name") String name,
            @Param("begin") String begin,
            @Param("end") String end
    );

    /*
    删除班级信息根据ID
     */
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    /*
    添加班级信息
     */
    void insert(Clazz clazz);

    /*
    根据ID查询班级信息
     */
    Clazz getById(Integer id);

    /*
    修改班级信息根据ID
     */
    void updateById(Clazz clazz);

    /*
    查询所有班级信息
     */
    List<Clazz> findToAll();
}
