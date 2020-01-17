package com.wjl.frame.service.impl;

import com.wjl.frame.ioc.annatation.MyComponent;
import com.wjl.frame.service.UserService;

@MyComponent
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("IOC创建实例");
    }

    @Override
    public void add() {
        System.out.println("调用了UserService中的add方法");
    }
}
