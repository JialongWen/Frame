package com.wjl.frame.ioc.di;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyEIByResource {

    //实际上只有存在IOC容器中的类才需要进行依赖注入
    //我们只需要传入IOC容器
    public static void resource(ConcurrentHashMap<String,Object> beans) throws IllegalAccessException {
        //便利我们需要扫描的类
        //这里要注意Map集合遍历的是他的条目
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object object = entry.getValue();
            Class<?> clazz = object.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            setFieldValue(declaredFields,beans,object);
        }

    }

    //在这个方法中对属性进行赋值
    private static void setFieldValue(Field[] declaredFields, ConcurrentHashMap<String, Object> beans,  Object object) throws IllegalAccessException {
        for (Field field : declaredFields) {
            //对遍历出来的属性获取他的注解
            MyResource myResource = field.getAnnotation(MyResource.class);
            //如果注解存在的话我们就对他进行赋值
            if (myResource != null) {
                String fieldName = field.getName();
                Object bean = beans.get(fieldName);
                if (bean != null) {
                    //默认使用属性名称，查找bean容器对象 1参数 当前对象 2参数 给属性赋值
                    field.setAccessible(true);
                    field.set(object, bean);
                }
            }
        }
    }

}
