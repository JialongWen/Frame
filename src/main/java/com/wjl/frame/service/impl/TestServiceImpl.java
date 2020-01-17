package com.wjl.frame.service.impl;

import com.wjl.frame.annotation.MyTransactional;
import com.wjl.frame.dao.TestDao;
import com.wjl.frame.service.TestService;
import com.wjl.frame.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TransactionUtils transactionUtils;

    @Autowired
    private TestDao testDao;

    public TestServiceImpl() {
        System.out.println("SpringIoc 创建实例");
    }

    @Override
    @MyTransactional(propagation= Propagation.REQUIRED)
    public void addTest(){
        try {
            testDao.insert("张三",23);
            testDao.insert("王五",23);
        }catch (Throwable e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
