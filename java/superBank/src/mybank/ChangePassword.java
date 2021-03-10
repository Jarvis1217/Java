package mybank;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ChangePassword implements ActionListener{
    public JTextField oldPassword,newPassword,checkPassword;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public ChangePassword() {
        iframe=new JFrame("��������");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        ip2=new JPanel();
        ip2.add(new JLabel("ԭ����:"));
        oldPassword=new JTextField(20);
        ip2.add(new JLabel("<html><br/><html>"));//����
        ip2.add(oldPassword);
 
        ip0=new JPanel();
        ip0.add(new JLabel("������:"));
        newPassword=new JTextField(20);
        ip0.add(new JLabel("<html><br/><html>"));//����
        ip0.add(newPassword);
 
        ip4=new JPanel();
        ip4.add(new JLabel("�ٴ�����������:"));
        checkPassword=new JTextField(20);
        ip4.add(new JLabel("<html><br/><html>"));//����
        ip4.add(checkPassword);
 
        ip3=new JPanel();
        confirm=new JButton("ȷ��");
        ip3.add(confirm);
        cancel=new JButton("����");
        ip3.add(cancel);
 
        iframe.add(ip2);
        iframe.add(ip0);
        iframe.add(ip4);
        iframe.add(ip3);
        iframe.add(confirm);
        iframe.add(cancel);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
 
        iframe.setBounds(500,500,350,300);
        confirm.addActionListener(this);
 
        cancel.addActionListener(this);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ȷ��")) {
            if (Test.currentAccount.password.equals(oldPassword.getText())) {
                try {
                    if(newPassword.getText().equals(checkPassword.getText())) {
 
                        Test.currentAccount.ChangePassword(newPassword.getText());
                        JOptionPane.showMessageDialog(null, "��������ɹ�");
                        iframe.setVisible(false);
                        Test.menu.mframe.setVisible(false);//�رղ˵�����
                        new LoginGui();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "������������벻һ��");
                    }
                }
             catch (Exception e1) {//�����˻����и������뺯�����쳣��������ʾ
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            } else {
 
                JOptionPane.showMessageDialog(null, "ԭ�������");
            }
        }
        else//������cancel
        {
            iframe.setVisible(false);
        }
    }
}
