package com.myself.JFrame;

import com.myself.Bean.User;
import com.myself.Dao.sqlDB;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Login {
    JFrame jf;
    private JTextField nameText;
    private JPasswordField passText;

    public Login() {
        jf = new JFrame("欢迎登录");
        jf.setResizable(false);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setBounds(100,100,600,500);
        jf.getContentPane().setLayout(null);

        JLabel userName = new JLabel("账户:");
        userName.setFont(new Font("宋体", Font.PLAIN, 15));
        userName.setBounds(143, 135, 76, 35);
        jf.getContentPane().add(userName);

        JLabel uesrPass = new JLabel("密码:");
        uesrPass.setFont(new Font("宋体", Font.PLAIN, 15));
        uesrPass.setBounds(143, 211, 76, 35);
        jf.getContentPane().add(uesrPass);

        // 用户名输入文本框
        nameText = new JTextField();
        nameText.setBounds(203, 135, 228, 35);
        jf.getContentPane().add(nameText);
        nameText.setColumns(10);

        // 密码输入文本框
        passText = new JPasswordField();
        passText.setBounds(203, 211, 228, 35);
        jf.getContentPane().add(passText);

        JButton logBtn = new JButton("登录");
        logBtn.setBounds(174, 325, 97, 44);
        jf.getContentPane().add(logBtn);

        JButton regBtn = new JButton("注册");
        regBtn.setBounds(334, 325, 97, 44);
        jf.getContentPane().add(regBtn);

        // 登录按钮监听
        logBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 获取用户输入
                String name = nameText.getText();
                String pass = String.valueOf(passText.getPassword());

                // 获取数据库数据进行对比
                String hql = "from User u where u.Name = '"+name+"'";
                Query query = sqlDB.init().createQuery(hql);
                List<User> user = query.list();
                if(user.size() == 0)
                    JOptionPane.showMessageDialog(null,
                            "用户不存在，请注册后登录!", "登录失败",JOptionPane.WARNING_MESSAGE);
                else if(!pass.equals(user.get(0).getPasswd()))
                    JOptionPane.showMessageDialog(null,
                            "密码有误，请核对后登录!", "登录失败",JOptionPane.WARNING_MESSAGE);
                else if(pass.equals(user.get(0).getPasswd()))
                    JOptionPane.showMessageDialog(null,"登录成功!");


                sqlDB.destroy();
            }
        });

        // 注册按钮监听
        regBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jf.dispose();
                new Register();
            }
        });

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        // 窗口皮肤设置
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 初始化hibernate框架
        sqlDB.init();
        sqlDB.destroy();

        // 展示窗体
        new Login();
    }
}
