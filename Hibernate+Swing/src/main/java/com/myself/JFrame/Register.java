package com.myself.JFrame;

import com.myself.Bean.User;
import com.myself.Dao.sqlDB;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

public class Register {
    JFrame jf;
    private JTextField nameText;
    private JPasswordField passText;
    private JPasswordField passAgain;

    public Register() {
        jf = new JFrame("用户注册");
        jf.setResizable(false);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setBounds(100,100,600,500);
        jf.getContentPane().setLayout(null);

        JLabel userName = new JLabel("账户:");
        userName.setFont(new Font("宋体", Font.PLAIN, 15));
        userName.setBounds(142, 92, 76, 35);
        jf.getContentPane().add(userName);

        JLabel uesrPass = new JLabel("密码:");
        uesrPass.setFont(new Font("宋体", Font.PLAIN, 15));
        uesrPass.setBounds(142, 168, 76, 35);
        jf.getContentPane().add(uesrPass);

        // 用户名输入文本框
        nameText = new JTextField();
        nameText.setBounds(202, 92, 228, 35);
        jf.getContentPane().add(nameText);
        nameText.setColumns(10);

        // 密码输入文本框
        passText = new JPasswordField();
        passText.setBounds(202, 168, 228, 35);
        jf.getContentPane().add(passText);

        JButton regBtn = new JButton("注册");
        regBtn.setBounds(149, 324, 127, 44);
        jf.getContentPane().add(regBtn);

        JLabel uesrPass_1 = new JLabel("确认密码:");
        uesrPass_1.setFont(new Font("宋体", Font.PLAIN, 15));
        uesrPass_1.setBounds(123, 232, 76, 35);
        jf.getContentPane().add(uesrPass_1);

        // 确认密码输入文本框
        passAgain = new JPasswordField();
        passAgain.setBounds(202, 232, 228, 35);
        jf.getContentPane().add(passAgain);

        JButton logBtn = new JButton("已有帐户登录");
        logBtn.setBounds(325, 324, 127, 44);
        jf.getContentPane().add(logBtn);

        // 注册按钮
        regBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameText.getText();
                String pass = String.valueOf(passText.getPassword());
                String passag = String.valueOf(passAgain.getPassword());

                if(!pass.equals(passag)) {
                    JOptionPane.showMessageDialog(null,
                            "两次密码输入不一致!", "注册失败", JOptionPane.WARNING_MESSAGE);
                }
                else {

                    String hql = "from User u where u.Name = '"+name+"'";
                    Query query = sqlDB.init().createQuery(hql);
                    List<User> user = query.list();
                    if(user.size() != 0)
                        JOptionPane.showMessageDialog(null,
                                "用户已存在，请返回登录!", "注册失败",JOptionPane.WARNING_MESSAGE);
                    else {
                        User us = new User();
                        us.setName(name);
                        us.setPasswd(pass);

                        Transaction tx = sqlDB.init().beginTransaction();
                        try {

                            sqlDB.init().save(us);
                            tx.commit();
                            JOptionPane.showMessageDialog(null,
                                    "注册成功,请返回登录!");

                        }catch (Exception err) {
                            err.printStackTrace();
                            tx.rollback();
                        }finally {
                            sqlDB.destroy();
                        }
                    }
                }
            }
        });

        // 返回登录按钮
        logBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jf.dispose();
                new Login();
            }
        });

        jf.setVisible(true);
    }
}
