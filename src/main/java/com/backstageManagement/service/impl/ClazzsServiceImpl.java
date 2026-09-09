package com.backstageManagement.service.impl;

import com.backstageManagement.mapper.ClazzsMapper;
import com.backstageManagement.pojo.Clazz;
import com.backstageManagement.pojo.ClazzQuery;
import com.backstageManagement.pojo.PageResult;
import com.backstageManagement.service.ClazzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService {

    @Autowired
    private ClazzsMapper clazzsMapper;

    /*
    查询所有班级信息
     */

    @Override
    public PageResult<Clazz> findAll(ClazzQuery query) {
        String name = query.getName();
        String begin = query.getBegin();
        String end = query.getEnd();
        Integer page = query.getPage();
        Integer pageSize = query.getPageSize();

        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;
        Integer start = (page - 1) * pageSize;

        List<Clazz> rows = clazzsMapper.findAll(name, begin, end, start, pageSize);
        Long total = clazzsMapper.countTotal(name, begin, end);

        rows.forEach(this::calculateStatus);
        return new PageResult<>(total, rows);
    }


    /**
     * 根据起止日期计算班级状态
     */
    private void calculateStatus(Clazz clazz) {
        LocalDate now = LocalDate.now();
        // 直接赋值，不再调用parse
        LocalDate begin = clazz.getBeginDate();
        LocalDate end = clazz.getEndDate();

        if (now.isBefore(begin)) {
            clazz.setStatus("未开班");
        } else if (now.isAfter(end)) {
            clazz.setStatus("已结课");
        } else {
            clazz.setStatus("已开班");
        }
    }


    /*
        删除班级信息
         */
    @Override
    public void deleteById(Integer id) {
        clazzsMapper.deleteById(id);

    }

    /*
    添加班级信息
     */
    @Override
    public void add(Clazz clazz) {
        //1.补全基础属性 -creatTime，updateTime
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口插入数据
        clazzsMapper.insert(clazz);
    }

    /*
    根据ID查询
     */
    @Override
    public Clazz seleteById(Integer id) {

        return clazzsMapper.getById(id);
    }

    /*
    修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        //1.补全基础属性 -updateTime
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口更新数据
        clazzsMapper.updateById(clazz);

    }

    @Override
    public List<Clazz> findToAll() {
        return clazzsMapper.findToAll();
    }
}
