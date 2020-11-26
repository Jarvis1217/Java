package com.myself.demo.demo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stuSystem {
    public static void main(String[] args) {

        LogInF login = new LogInF();
    }
}

//凡是xxL：代表Lable标签； xxT：代表TextField文本框；xxP：代表Panel面板
//登录窗口：
class LogInF extends JFrame {
    private final int width = 300;
    private final int height = 150;//窗口大小

    //以下用户名、密码的标签和文本框，填写密码的组件用"密码框"，确认和取消按钮：
    private final JLabel userL = new JLabel("用户名：");
    private final JLabel pwL = new JLabel("密    码：");
    private final JTextField userT = new JTextField(10);
    private final JPasswordField pwT = new JPasswordField(10);
    private final JButton confirm = new JButton("确认");
    private final JButton cancel = new JButton("取消");

    //以下三个面板，分别装用户名的标签的文本框，密码的标签和文本框，2个按钮：
    private final JPanel userP = new JPanel();
    private final JPanel pwP = new JPanel();
    private final JPanel btnP = new JPanel();

    //确认按钮 监听器接口：
    private final ConfirmListener b1 = new ConfirmListener();
    //取消按钮 监听器接口：
    private final CancleListener b2 = new CancleListener();

    //构造函数
    public LogInF() {
        super("登录界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);//设置大小

//网格布局，3个面板，3行1列：
        setLayout(new GridLayout(3, 1));

//添加3个面板：
        add(userP);
        add(pwP);
        add(btnP);

//面板上添加组件：

        //用户面板添加用户名标签和编辑框：
        userP.add(userL);
        userP.add(userT);

        //密码面板添加密码标签和编辑框：
        pwP.add(pwL);
        pwP.add(pwT);

        //按钮面板添加2个按钮：
        btnP.add(confirm);
        btnP.add(cancel);

        //输入密码时显示*，这里的编辑框是密码框
        pwT.setEchoChar('*');
//按钮添加监听器
        confirm.addActionListener(b1);
        cancel.addActionListener(b2);

//设置窗口位置，可见，不可更改大小
        setLocation(300, 250);
        setVisible(true);
        setResizable(false);
    }

    //ActionListener接口只有一个方法：actionPerformed
    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //判断账号密码是否正确：
            String user = userT.getText();
            String pw = String.valueOf(pwT.getPassword());

            if (user.equals("admin") && pw.equals("root")) {
                JOptionPane.showMessageDialog(rootPane, "登陆成功！");
                dispose();//关闭登录窗口
                InfoF infof = new InfoF();//打开个人信息窗口

            } else {
                //提示错误
                JOptionPane.showMessageDialog(rootPane, "用户名或密码错误！");
            }
        }
    }

    class CancleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(-1);//退出
        }
    }
}

//学生信息窗口：
class InfoF extends JFrame {
    private final int width = 350;
    private final int height = 450;//窗口大小

    //以下所有的面板：学号面板，姓名、性别、出生日期、团员否、专业、地址、简介面板：
    private final JPanel snoP = new JPanel();
    private final JPanel nameP = new JPanel();
    private final JPanel sexP = new JPanel();
    private final JPanel birthP = new JPanel();
    private final JPanel memP = new JPanel();
    private final JPanel spetP = new JPanel();
    private final JPanel addP = new JPanel();
    private final JPanel brifeP = new JPanel();
    private final JPanel btnP = new JPanel();

    //以下各标签：
    private final JLabel snoL = new JLabel("学         号：");
    private final JLabel nameL = new JLabel("姓         名：");
    private final JLabel sexL = new JLabel("性         别：");
    private final JLabel birthL = new JLabel("出生日期：");
    private final JLabel memL = new JLabel("团         员：");
    private final JLabel spetL = new JLabel("专         业：");
    private final JLabel addL = new JLabel("家庭地址：");
    private final JLabel brifeL = new JLabel("简         介：");

    //以下各文本框或复选框或按钮组或单选按钮或组合框等：
//要让单选按钮（单选框）表现排他行为，需要把它们加入到一个按钮组ButtonGroup中
    private final JTextField snoT = new JTextField(10);
    private final JTextField nameT = new JTextField(6);
    private final JTextField birthT = new JTextField(10);
    private final JTextField addT = new JTextField(15);

    private final ButtonGroup sexBtn = new ButtonGroup();
    private final JRadioButton rb1 = new JRadioButton("男", false);
    private final JRadioButton rb2 = new JRadioButton("女", true);

    private final JCheckBox memC = new JCheckBox("是");

    private final String[] spetStrings = {"计算机", "自动化", "汉语言文学"};
    private final JComboBox spetC = new JComboBox(spetStrings);

    private final JTextArea brifeT = new JTextArea(8, 20);// 简历文本区域


    //以下2个按钮，保存和取消：
    private final JButton store = new JButton("保存");
    private final JButton cancle = new JButton("取消");// 保存、取消按钮

    //构造函数：
    public InfoF() {
        super("学生信息录入窗口");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(500, 200);

//BoxLayout和GridLayout类似，BoxLayout可以在水平或者垂直方向控制组件的位置
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

//以下各面板设置流式布局，左对齐，并添加各标签和文本框
        snoP.setLayout(new FlowLayout(FlowLayout.LEFT));
        snoP.add(snoL);
        snoP.add(snoT);

        nameP.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameP.add(nameL);
        nameP.add(nameT);

        sexP.setLayout(new FlowLayout(FlowLayout.LEFT));
        sexP.add(sexL);
//单选按钮要添加到一个按钮组中：
        sexBtn.add(rb1);
        sexBtn.add(rb2);
//2个单选按钮加入到性别面板中：
        sexP.add(rb1);
        sexP.add(rb2);

        birthP.setLayout(new FlowLayout(FlowLayout.LEFT));
        birthP.add(birthL);
        birthP.add(birthT);

        memP.setLayout(new FlowLayout(FlowLayout.LEFT));
        memP.add(memL);
        memP.add(memC);

        spetP.setLayout(new FlowLayout(FlowLayout.LEFT));
        spetP.add(spetL);
        spetP.add(spetC);

        addP.setLayout(new FlowLayout(FlowLayout.LEFT));
        addP.add(addL);
        addP.add(addT);

        brifeP.setLayout(new FlowLayout(FlowLayout.LEFT));
        brifeP.add(brifeL);
        brifeP.add(brifeT);
//简历文本区域不可编辑：
        brifeT.setEditable(false);

//2个按钮添加到按钮面板中：
        btnP.add(store);
        btnP.add(cancle);

//以下添加各面板，是BoxLayout的垂直布局方式：
        add(snoP);
        add(nameP);
        add(sexP);
        add(birthP);
        add(memP);
        add(spetP);
        add(addP);
        add(brifeP);
        add(btnP);

//按钮添加监听器：比登录窗口里实现的方法简洁一点：
        store.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                brifeT.setText("");//先清空
                brifeT.append("学号：" + snoT.getText() + "\n");
                brifeT.append("姓名：" + nameT.getText() + "\n");
                //一个条件表达式：
                brifeT.append("性别：" + (rb1.isSelected() ? "男" : "女") + "\n");
                brifeT.append("出生日期：" + birthT.getText() + "\n");
                //又一个条件表达式：
                brifeT.append("是否为团员：" + (memC.isSelected() ? "是" : "否") + "\n");
                brifeT.append("专业：" + spetC.getSelectedItem() + "\n");
                brifeT.append("家庭住址：" + addT.getText() + "\n");

            }
        });

//取消按钮增加监听器：
        cancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
//设置可见，不可调整大小
        setVisible(true);
        setResizable(false);
    }
}
