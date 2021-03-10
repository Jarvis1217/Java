package mybank;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
 
public class OutMoney implements ActionListener{
    public JTextField money;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3;
    public JButton confirm,cancel,exit;
    public JLabel yue;//���
    public OutMoney() {
        iframe=new JFrame("ȡ��");
 
        ip0=new JPanel();
        ip0.add(new JLabel("�˻�id:"+Test.currentAccount.id));
 
        ip1=new JPanel();
        yue=new JLabel("�˻����:"+Test.currentAccount.money);
        ip1.add(yue);
 
        ip2=new JPanel();
        ip2.add(new JLabel("ȡ����:"));
        money=new JTextField(20);
        ip2.add(money);
 
        ip3=new JPanel();
        confirm=new JButton("ȷ��");
        ip3.add(confirm);
        cancel=new JButton("����");
        ip3.add(cancel);
 
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
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
        if(e.getActionCommand().equals("ȷ��"))//���ȷ����ť
        {
            try {
 
                Test.currentAccount.outMoney(Integer.parseInt(money.getText()));
 
                JOptionPane.showMessageDialog(null, "ȡ��ɹ�");//����
                yue.setText("�˻����:"+Test.currentAccount.money);//���������ʾ
            }
            catch (ClassCastException e1)
            {
 
                JOptionPane.showMessageDialog(null, "�����������ʹ�������������");//����Test����outmoney�������쳣
 
            }
            catch (Exception e1)
            {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        }
        else
        {
            iframe.setVisible(false);//����
 
        }
    }
}
