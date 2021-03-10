package com.myself.Bean;

import java.io.Serializable;

public class User implements Serializable {
    private int ID;
    private String Name;
    private String Passwd;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Passwd='" + Passwd + '\'' +
                '}';
    }
}
