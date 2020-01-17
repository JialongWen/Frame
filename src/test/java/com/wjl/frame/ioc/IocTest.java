package com.wjl.frame.ioc;

import com.wjl.frame.exception.ClassPathApplicationContextException;
import com.wjl.frame.ioc.annatation.MyClassPathXmlApplicationContext;
import com.wjl.frame.ioc.xml.ClassPathApplicationContext;
import com.wjl.frame.service.TestService;


public class IocTest {


    public static void main(String[] args) {
        try {
            MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("com.wjl.frame");
            TestService testService = (TestService)context.getBean("testServiceImpl");
            testService.addTest();
            System.out.println(testService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
