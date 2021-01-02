package com.myself.demo;

import com.myself.demo.Dao.UserMapper;
import com.myself.demo.pojo.User;
import org.apache.ibatis.session.SqlSession;
import com.myself.demo.utils.mybatisUtils;

import java.util.List;

public class UserDaoTest {
    static SqlSession sqlSession;

    public static void main(String[] args) {

        try {

            sqlSession = mybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateUser("abc");
            sqlSession.commit();

        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
