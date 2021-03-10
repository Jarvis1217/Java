package BankOS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class myFrame 
{
	public static void main(String[] args)
	{
		//设置窗体信息
		Frame fr=new Frame("BankOS");
		fr.setVisible(true);
		fr.setLocation(400,100);
		fr.setSize(800,600);
		//关闭窗口
		fr.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		//窗口布局
		fr.setLayout(null);
		JLabel t=new JLabel("Welcome to BankOS");
		fr.add(t);
		t.setBounds(300,80,200,220);
		t.setFont(new Font("宋体",Font.BOLD,20));
		//添加按钮
		Button b1=new Button("登 陆");
		Button b2=new Button("清 空");
		fr.add(b1);fr.add(b2);
		b1.setBounds(300,400,70,40);
		b2.setBounds(400,400,70,40);
		//设置文本框
		JLabel lab1=new JLabel("账 号:");
		JLabel lab2=new JLabel("密 码:");
		JTextField tex1=new JTextField();
		JPasswordField tex2=new JPasswordField();
		fr.add(lab1);fr.add(lab2);
		fr.add(tex1);fr.add(tex2);
		tex1.setBounds(300,255,200,25);
		tex2.setBounds(300,295,200,25);
		lab1.setBounds(260,255,200,25);
		lab2.setBounds(260,295,200,25);
	}
}
