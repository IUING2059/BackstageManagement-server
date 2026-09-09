package com.backstageManagement.controller;

import com.backstageManagement.pojo.Emp;
import com.backstageManagement.pojo.Result;
import com.backstageManagement.pojo.UpdatePwdDTO;
import com.backstageManagement.service.EmpService;
import com.backstageManagement.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
修改密码
 */
@Slf4j
@RestController
public class UpdatePwdController {
    @Autowired
    private EmpService empService;

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody UpdatePwdDTO dto){
        // 获取登录用户id
        Integer id = CurrentHolder.getCurrentId();
        log.info("修改密码,id:{}",id);
        // 查询数据库员工完整对象（带主键id）
        Emp emp = empService.getInfo(id);
        log.info("修改密码，员工信息：{}",emp);

        if(emp == null){
            return Result.error("该用户不存在");
        }

        // 校验原密码（前端传过来的原密码明文）
        if (!emp.getPassword().equals(dto.getOldPwd())) {
            return Result.error("原密码错误");
        }

        // 可选校验：新密码不能为空
        if(dto.getNewPwd() == null || dto.getNewPwd().isBlank()){
            return Result.error("新密码不能为空");
        }
        // 可选校验：新密码不能等于旧密码
        if(emp.getPassword().equals(dto.getNewPwd())){
            return Result.error("新密码不能与原密码相同");
        }

        //更新
        Emp empDb = empService.getInfo(id);
        empDb.setPassword(dto.getNewPwd());
        empService.updateById(empDb);

        return Result.success("修改密码成功");
    }



}

