package com.myself.shop.dao.impl;

import com.myself.shop.dao.UserDao;
import com.myself.shop.pojo.User;
import com.myself.shop.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByName(String uname) {
        Connection connection = DBUtil.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where uname = ?");
            preparedStatement.setString(1,uname);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new User(resultSet.getString("uname"), resultSet.getString("passwd"));
            }

            preparedStatement.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return new User();
    }
}
