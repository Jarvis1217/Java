package com.myself.servlet01.Dao.User;

import com.myself.servlet01.pojo.User;

public interface UserMapper {
    User getUserByName(String userName);
}
