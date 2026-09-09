package com.backstageManagement.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backstageManagement.mapper.OperateLogMapper;
import com.backstageManagement.pojo.OperateLog;
import com.backstageManagement.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知
    @Around("@annotation(com.backstageManagement.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));

        // ========== 核心修改：序列化对象为标准JSON ==========
        ObjectMapper objectMapper = new ObjectMapper();
        String returnValueStr;
        if (result == null) {
            returnValueStr = "void";
        } else {
            // 转为 {"code":1,"msg":"success","data":null} 格式
            returnValueStr = objectMapper.writeValueAsString(result);
        }
        operateLog.setReturnValue(returnValueStr);
        operateLog.setCostTime(costTime);

        //保存日志
        log.info("记录操作日志：{}",operateLog);
        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }

    // 示例方法，获取当前用户ID
    private int getCurrentUserId() {
        Integer currentId = CurrentHolder.getCurrentId();
        // 如果currentId是null，返回0；有值就返回currentId本身
        return currentId == null ? 0 : currentId;
    }

}