package com.wjl.frame.annotation;

import org.springframework.transaction.annotation.Propagation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTransactional {
    Propagation propagation() default  Propagation.REQUIRED;
}
