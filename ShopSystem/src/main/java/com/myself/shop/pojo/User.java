package com.myself.shop.pojo;

public class User {
    private String uname;
    private String passwd;

    public User() {}

    public User(String uname, String passwd) {
        this.uname = uname;
        this.passwd = passwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
