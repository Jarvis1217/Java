package com.myself.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class sqlDB {
    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    public static Session init() {
        // 初始化注册服务对象
        registry = new StandardServiceRegistryBuilder().configure().build();
        // 获取session工厂
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        // 创建session连接
        session = sessionFactory.openSession();
        return session;
    }

    public static void destroy() {
        try {

            if(session != null && session.isOpen())
                session.close();
            if(sessionFactory != null)
                sessionFactory.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
