package com.myself.demo.pojo;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class User {
    private int id;
    private String uname;
    private String upwd;

    public User() {
    }

    public User(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}
