package com.wjl.frame.ioc.annatation;

import com.wjl.frame.ioc.di.MyEIByResource;
import com.wjl.frame.ioc.util.ClassUtil;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class MyClassPathXmlApplicationContext {

    private String packageName;
    //Spring容器
    private ConcurrentHashMap<String,Object> beans = null;

    public MyClassPathXmlApplicationContext(String packageName) throws Exception {
        //此处传进来的需要扫描的包路径实际上等同于Spring XML配置文件中的扫包路径配置
        //此处因为XML的解析已经编写过了所有就不再重点编码(略去),让路径作为一个参数直接传进来
        this.packageName = packageName;
        beans = new ConcurrentHashMap<String,Object>();
        initBeans();
        //我认为在初始化容器后就应该进行依赖注入所有我在这里调用了依赖注入的实现类静态方法
        //并将IOC容器传递给他
        MyEIByResource.resource(beans);
    }

    private void initBeans() throws Exception {
        //使用Java的反射机制扫包，获取当前包下的所有类
        List<Class<?>> classes = ClassUtil.getClassList(packageName, true);
        //判断类上是否存在注入bean的注解
        ConcurrentHashMap<String, Object> classExisAnnotation = findAnnotation(classes);
        if(classExisAnnotation == null || classExisAnnotation.isEmpty()){
            //此处抛出异常仅作测试使用
            //正常源码中不会爆出异常
            throw new Exception("No classes need to be loaded !");
        }
    }

    //使用java的反射机制进行初始化
    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("beanId can not be empty!");
        }
        Object object = beans.get(beanId);
        if (object == null){
            throw new Exception("No bean found !"+beanId);
        }
        return object;
    }

    //创建实例对象
    private Object newInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    private ConcurrentHashMap<String,Object> findAnnotation(List<Class<?>> classes) throws InstantiationException, IllegalAccessException {
        for (Class<?> clazz : classes) {
            //判断是否存在注解
            //此处获取固定的注解
            //当然我们也可以获取到一个注解的数组将其遍历来判断是哪一个注解
            MyComponent annotation = clazz.getAnnotation(MyComponent.class);
            if (annotation != null){
                //这里存入的id为做处理默认将首字母小写作为beanId
                //当然我们也可以判断注解上是否存在参数将参数作为beanId,因此处不是重点所以为做处理
                beans.put(toLowerCase(clazz),newInstance(clazz));
                continue;
            }
        }
        return beans;
    }

    private String toLowerCase(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        if (Character.isLowerCase(simpleName.charAt(0))){
                return  simpleName;
        }
        return (new StringBuilder()).append(Character.toLowerCase(simpleName.charAt(0))).append(simpleName.substring(1)).toString();
    }

}
