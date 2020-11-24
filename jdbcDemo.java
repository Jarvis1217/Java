package com.myself.test;

import java.sql.*;

public class Demo1 {
    static String Driver = "com.mysql.cj.jdbc.Driver";
    static String Url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    static String User = "root";
    static String Pass = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {

            // 注册并打开连接
            Class.forName(Driver);
            conn = DriverManager.getConnection(Url,User,Pass);

            // 查询数据
            stmt = conn.createStatement();
            String sql1;
            sql1 = "SELECT name,age,gender FROM student";
            ResultSet rs = stmt.executeQuery(sql1);

            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");

                System.out.println("name=" + name + " age=" + age + " gender=" + gender);
            }

//            // 插入数据
//            String sql2;
//            sql2 = "INSERT INTO student(name,age,gender) VALUES('李强',21,'男')";
//            if(!stmt.execute(sql2)) {
//                System.out.println("插入成功!");
//            }else {
//                System.out.println("插入失败!");
//            }

//            // 删除数据
//            String sql3;
//            sql3 = "DELETE FROM student WHERE name='韩梅梅'";
//            if(!stmt.execute(sql3)) {
//                System.out.println("删除成功!");
//            }else {
//                System.out.println("删除失败!");
//            }

//            // 修改数据
//            String sql4;
//            sql4 = "UPDATE student SET age=21 WHERE name='李丽'";
//            if(!stmt.execute(sql4)) {
//                System.out.println("修改成功!");
//            }else {
//                System.out.println("修改失败!");
//            }

        }catch(Exception e) {

            e.printStackTrace();

        }finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
