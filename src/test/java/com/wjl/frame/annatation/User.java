package com.wjl.frame.annatation;

import com.wjl.frame.annotation.AddAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class User {
    @AddAnnotation(name = "张三",age = 18,arrays = {"1"})
    public void add(){

    }

    public void del(){

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.wjl.frame.annatation.User");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            AddAnnotation annotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if (annotation == null){
                //未曾获取到注解
                //也就是该方法上没有该注解
                System.out.println("该方法上未加注解"+method.getName());
                continue;
            }
            //获取到了该注解
            System.out.println("name:"+annotation.name()+"age:"+annotation.age()+"arrays:"+annotation.arrays());
        }

    }
}
