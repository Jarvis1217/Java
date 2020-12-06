package com.myself.test;

import com.myself.demo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class mainTest {
    static StandardServiceRegistry registry = null;
    static SessionFactory sessionFactory = null;
    static Session session = null;
    static Transaction tx = null;

    public static void main(String[] args) {
        try {
            // 初始化注册服务对象
            registry = new StandardServiceRegistryBuilder().configure().build();
            // 获取session工厂
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            // 创建session连接
            session = sessionFactory.openSession();
            // 开启事务
            tx = session.beginTransaction();

            // 创建实例
//            User user = new User();
//            user.setName("李明");
//            user.setPasswd("root123");
//            session.save(user);

            // 获取数据
//            User user = session.get(User.class,2);
//            System.out.println(user);

            // 获取表中全部数据
//            String hql = "from User";
//            Query query = session.createQuery(hql);
//            List<User> user = query.list();
//            for (Object us : user) {
//                System.out.println(us);
//            }

            // 更新数据
//            String hql = "update User us set us.passwd=?1 where us.name=?2";
//            Query query = session.createQuery(hql);
//
//            query.setParameter(1, "admin123");
//            query.setParameter(2, "李华");
//
//            query.executeUpdate();

            // 删除数据
//            session.createQuery("delete User where name=?1")
//                    .setParameter(1,"李明").executeUpdate();


            // 提交事务
            tx.commit();

        }catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            tx.rollback();
        }finally {
            if(session != null && session.isOpen())
                session.close();
        }
    }
}
