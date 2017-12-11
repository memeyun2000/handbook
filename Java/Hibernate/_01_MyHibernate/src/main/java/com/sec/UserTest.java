package com.sec;

import com.sec.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserTest{
    public static void main(String[] args) {
        System.out.println("connect to sqlite with hibernate");
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        //2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    
        /****上面是配置准备，下面开始我们的数据库操作******/
        Session session = sessionFactory.openSession();//从会话工厂获取一个session
    
        Transaction transaction = session.beginTransaction();//开启一个新的事务
        User user = new User();
        user.setName("zengh");
        session.save(user);
        transaction.commit();//提交事务
    }
}