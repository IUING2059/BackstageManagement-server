package com.backstageManagement.controller;

import com.backstageManagement.pojo.Result;
import com.backstageManagement.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    /*
    文件上传
    本地存储
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接受的参数：{},{},{}",name,age,file);
//        //获取文件名称
//        String originalFilename = file.getOriginalFilename();
//        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀
//        String newFileName= UUID.randomUUID().toString()+extension;//随机生成文件名称
//        //保存文件
//        file.transferTo(new File("B:/桌面/exercise/web-ai-project02/images/"+newFileName));
//        return Result.success();
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());
        //将文件上传给阿里云OSS
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传的url地址：{}", url);
        return Result.success(url);
    }
}
