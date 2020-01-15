package com.wjl.frame.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target设置使用的级别
 * @Retention生命周期级别
 * @by wjl
 */
//元注解
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddAnnotation {
    String name() default "默认名称";
    int age() default 0;
    String[] arrays();
}
