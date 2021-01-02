package com.myself.demo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class mybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    // 获取sqlSessionFactory
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 获取sqlSession实列
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
