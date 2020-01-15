/*
package com.wjl.frame.aop;

import com.wjl.frame.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
@Aspect
public class TransactionAop {

    @Autowired
    private TransactionUtils transactionUtils;

    @Pointcut("execution(* com.wjl.frame.service.impl.TestServiceImpl.addTest(..))")
    public void serviceImpl(){}

    @AfterThrowing("serviceImpl()")
    public void afterThrowing(){
        System.out.println("回滚事务");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Around("serviceImpl()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println("开启事务");
            TransactionStatus transactionStatus = transactionUtils.begin();
            joinPoint.proceed();
            System.out.println("提交事务");
            transactionUtils.commit(transactionStatus);
    }

}
*/
