package com.myself.shop.dao;

import com.myself.shop.pojo.User;

public interface UserDao {
    User getUserByName(String uname);
}
