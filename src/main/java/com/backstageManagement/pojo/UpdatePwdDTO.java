package com.backstageManagement.pojo;

import lombok.Data;

@Data
public class UpdatePwdDTO{
    //原来的密码
    private String oldPwd;
    //新密码
    private String newPwd;
    //再次确定密码
    private String rePwd;
}
