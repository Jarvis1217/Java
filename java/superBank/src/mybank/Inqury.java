package mybank;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Inqury implements ActionListener{
    public JFrame iframe;
    public JPanel ip0,ip1,ip2;
    public JTextArea inquryresult;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    public Inqury() {
        iframe=new JFrame("��ѯ");
 
        ip0=new JPanel();
        ip0.add(new JLabel("�˻�id:"+Test.currentAccount.id));
        ip1=new JPanel();
        yue=new JLabel("�˻����:"+Test.currentAccount.money);
        ip1.add(yue);
        ip2=new JPanel();
        inquryresult=new JTextArea(10,30);
        ip2.add(inquryresult);
        confirm=new JButton("��ѯ��¼");
        confirm.addActionListener(this);
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(confirm);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
 
        iframe.setBounds(500,500,350,300);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("��ѯ��¼"));
        {
            //Test.recordString�Ǵ��˻��ĵ��ж�ȥ�����ַ���
            //��д���ı�ʱ/r/n���ǻ���,����java��\r\n�����������У�����Test.recordString��һ��һ�ж�ȡ������ƴ���ϵģ����Բ�û�л��з������������滻��һ��\n
            inquryresult.setText(Test.recordString.toString().replace("Ԫ","Ԫ\n").replace("null",""));//ȥ��������ַ����е�null,����Ԫ�滻ΪԪ\r\n�����л���
            yue.setText("�˻����:"+Test.currentAccount.money);//������ʾ���
        }
    }
}
