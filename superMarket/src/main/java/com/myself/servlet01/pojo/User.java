package com.myself.servlet01.pojo;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class User {
    private String userName;
    private String userPasswd;
    private Integer isAdmin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
