package com.wjl.frame.service.impl;

import com.wjl.frame.annotation.MyTransactional;
import com.wjl.frame.dao.TestDao;
import com.wjl.frame.ioc.annatation.MyComponent;
import com.wjl.frame.ioc.di.MyResource;
import com.wjl.frame.service.TestService;
import com.wjl.frame.service.UserService;

@MyComponent
public class TestServiceImpl implements TestService {

    @MyResource
    private UserService userServiceImpl;

    public TestServiceImpl() {
        System.out.println("SpringIoc 创建实例");
    }

    @Override
    public void addTest(){
        userServiceImpl.add();
    }
}
