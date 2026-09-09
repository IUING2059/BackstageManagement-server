package com.backstageManagement.mapper;

import com.backstageManagement.pojo.Student;
import com.backstageManagement.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface StudentsMapper {
    /*
    查询学员数据
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /*
    添加学员数据
     */
    void insert(Student student);

    /*
    根据ID查询学员数据
     */
    Student getById(Integer id);

    /*
    修改学员数据
     */
    void update(Student student);

    /*
    学员学历统计
     */
    @MapKey("d")
    List<Map<String, Objects>> getStudentDegreeData();

    /*
    学员数量统计
     */
    @MapKey("c")
    List<Map<String, Object>> countStudentCountData();

    /*
    删除学员根据Id
     */
    void deleteBy(@Param("ids") List<Integer> ids);

    //违纪处理
    int updateViolationInfo(@Param("id") Integer id, @Param("score") Integer score);
}
