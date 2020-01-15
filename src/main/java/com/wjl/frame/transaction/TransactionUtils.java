package com.wjl.frame.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
//Spring默认单例
@Scope("prototype")//每个事务都是新的实例,解决线程安全问题
public class TransactionUtils {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionStatus transaction;

    public TransactionStatus begin(){
        transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    public void commit(TransactionStatus transaction){
        dataSourceTransactionManager.commit(transaction);
    }

    public void rollback(){
        dataSourceTransactionManager.rollback(transaction);
    }
}
