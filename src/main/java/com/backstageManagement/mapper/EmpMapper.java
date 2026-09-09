package com.backstageManagement.mapper;

import com.backstageManagement.pojo.Emp;
import com.backstageManagement.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
员工信息
 */
@Mapper
public interface EmpMapper {
//    /**
//     * 查询员工总记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
//    public long count();
//
//    /**
//     * 查询员工列表 分页查询
//     */
//    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id=d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //@Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    /*
    查询员工列表
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /*
    添加员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取生成的主键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /*
    批量删除员工根据ID
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /*
    根据ID查询员工信息以及员工的工作信息
     */
    Emp getById(Integer id);

    /*
    修改员工信息
     */
    void updateById(Emp emp);

    /*
    统计员工信息以及员工工作信息
     */
    @MapKey("position")
    List<Map<String,Object>> countEmpJobData();

    /*
    统计员工性别信息
     */
    @MapKey("name")
    List<Map<String, Objects>> countEmpGenderData();

    /*
    查询全部员工信息
     */
    List<Emp> getAll();

    /*
    根据部门ID查询员工数量
     */
    Integer countByDeptId(Integer id);

    /*
    根据用户名和密码查询员工信息
     */
    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);


}
