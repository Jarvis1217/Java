package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Lottery extends JFrame {
    static JTextField textField;
    static JTextField textField_1;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Lottery frame = new Lottery();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public Lottery() {
        Font fn = new Font("宋体",Font.BOLD,15);//定义字体，并用构造方法初始化
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//定义窗口可关闭
        setBounds(100, 100, 625, 328);//窗口大小和位置
        getContentPane().setLayout(null);//绝对布局

        JDesktopPane desktopPane = new JDesktopPane();//定义小窗口
        //desktopPane.setToolTipText("输入观众姓名按回车");
        desktopPane.setBounds(24, 12, 171, 286);//设置文本框基本参数
        getContentPane().add(desktopPane);//添加界面

        JLabel lblNewLabel = new JLabel("　　输入观众姓名按回车");//为上面的小窗口定义标签名称
        lblNewLabel.setBounds(0, 12, 171, 13);
        desktopPane.add(lblNewLabel);

        textField = new JTextField();//文本框
        textField.setBounds(10, 37, 149, 26);
        desktopPane.add(textField);
        textField.setColumns(30);

        List list = new List();//列表定义，用于存储姓名
        desktopPane.setLayer(list, 100);
        list.setMultipleSelections(true);
        list.setBounds(8, 69, 151, 169);
        desktopPane.add(list);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBounds(216, 12, 317, 286);
        getContentPane().add(desktopPane_1);

        JLabel lblNewLabel_1 = new JLabel("抽取观众成员");
        lblNewLabel_1.setBounds(12, 12, 220, 19);
        desktopPane_1.add(lblNewLabel_1);

        JLabel label = new JLabel("本次抽取的观众成员为");
        label.setBounds(12, 32, 275, 27);
        desktopPane_1.add(label);

        JTextArea textArea = new JTextArea(3,20);
        textArea.setBounds(12, 82, 281, 192);
        desktopPane_1.add(textArea);
        textArea.setFont(fn);

        JButton btnNewButton = new JButton("抽取");
        btnNewButton.setBounds(543, 218, 70, 23);
        getContentPane().add(btnNewButton);

        int i=0;
        ArrayList<String> str = new ArrayList<String>(); 
        textField.addKeyListener(new KeyListener() {//文本框键盘监听
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {//当出现回车按键时间，会处理文本框的字符串，将他们进行储存，添加到列表
                if(e.getKeyChar()!='\n')
                    return ;
                String name = textField.getText();
                if(name.isEmpty())
                    return ;
                list.add(name+"\n");
                str.add(name);
                textField.setText("");
            }
        });

        btnNewButton.addActionListener(new ActionListener() {//按钮监听，输出随机生成的标号在字符串数组中的所对应下标的名字
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                int n = str.size();
                int x = (int) (Math.random()*n);
                String s0 = str.get(x);
                String s1 = "\t\t"+s0+"\n恭喜"+s0+"成为本次幸运观众。";
                textArea.setText(s1);
            }
        });
   }
}