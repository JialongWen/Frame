package com.wjl.frame.aop;

import com.wjl.frame.annotation.MyTransactional;
import com.wjl.frame.transaction.TransactionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

//自定义事务注解的实现
@Aspect
@Component
public class AopExtTransaction {

    @Autowired
    private TransactionUtils transactionUtil;

    @Value("${my-transaction-path}")
    private String transactionPath;

    @Pointcut("execution(* com.wjl.frame.service..*(..))")
    private void serviceImpl(){

    }

    @AfterThrowing("serviceImpl()")
    private void afterThrowing(JoinPoint joinPoint) throws Throwable {
        Method method = rollbackMethod(joinPoint);
        rollback(getMyTransactional(method));

    }

    private void rollback(MyTransactional myTransactional) {
        if (myTransactional == null){
            return;
        }
        System.out.println("事务回滚");
        transactionUtil.rollback();
    }

    @Around("serviceImpl()")
    private void around(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获取代理对象的方法
        Method objMenthod = getObjMethod(joinPoint);
        //2.获取方法上的注释
        //3.如果存在事务的注解，开启事务
        TransactionStatus transactionStatus = begin(getMyTransactional(objMenthod));
        //4.调用目标代理对象
        joinPoint.proceed();
        //5判断方法上是否就上注解
        //6.如果存在注解，提交事务
        //判断是否存在为提交的事务，存在就提交
        commit(transactionStatus);
    }

    private void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null){
            transactionUtil.commit(transactionStatus);
            System.out.println("提交事务");
        }
    }

    private TransactionStatus begin(MyTransactional myTransactional) {
        if (myTransactional == null){
            return null;
        }
        System.out.println("开启事务");
        return transactionUtil.begin();
    }

    private MyTransactional getMyTransactional(Method objMenthod) {
        return objMenthod.getDeclaredAnnotation(MyTransactional.class);
    }

    private Method getObjMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        //获取方法的名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象
        Class<?> clazz = joinPoint.getTarget().getClass();
        //获取目标对象的类型
        Class[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = clazz.getMethod(methodName, par);
        return objMethod;
    }

    private Method rollbackMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        //获取方法的名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象
        Class<?> clazz = joinPoint.getTarget().getClass();
        //获取目标对象的类型
        Class[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = clazz.getMethod(methodName, par);
        return objMethod;
    }
}
