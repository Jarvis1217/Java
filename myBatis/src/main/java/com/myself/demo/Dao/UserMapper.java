package com.myself.demo.Dao;

import com.myself.demo.pojo.User;
import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    User getUserByName(String name);
    void addUser(User user);
    void deleteUser(String name);
    void updateUser(String name);
}
