package BankOS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class myFrame 
{
	public static void main(String[] args)
	{
		//���ô�����Ϣ
		Frame fr=new Frame("BankOS");
		fr.setVisible(true);
		fr.setLocation(400,100);
		fr.setSize(800,600);
		//�رմ���
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
		//���ڲ���
		fr.setLayout(null);
		JLabel t=new JLabel("Welcome to BankOS");
		fr.add(t);
		t.setBounds(300,80,200,220);
		t.setFont(new Font("����",Font.BOLD,20));
		//��Ӱ�ť
		Button b1=new Button("�� ½");
		Button b2=new Button("�� ��");
		fr.add(b1);fr.add(b2);
		b1.setBounds(300,400,70,40);
		b2.setBounds(400,400,70,40);
		//�����ı���
		JLabel lab1=new JLabel("�� ��:");
		JLabel lab2=new JLabel("�� ��:");
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
