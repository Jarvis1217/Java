package mybank;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
 
 
public class LoginGui implements ActionListener{//ʵ�ּ������Ľӿ�
    private JFrame frame;
    private JPanel p0,p1,p2,p3,p4;//p4����ȷ������ʱ������򣻵��register��ť����
 
    private JTextField userName;
    private JTextField passWord,passwordCheck;
    private JButton login;
    private JButton register;
    private Reader fw;
    Boolean regirsterable=true;//�����Ƿ���ע��ı���
 
 
    public LoginGui() {
        frame=new JFrame("��¼ATM");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڵĵ�����Ͻǵ�x�Ĵ���ʽ���������õ����˳�����
        p0=new JPanel();
 
        p0.add(new JLabel("�й�������������ATM"));
        frame.add(p0);
 
        p1=new JPanel();
        p1.add(new JLabel("\tUserName:"));
        userName=new JTextField(20);
        p1.add(userName);
 
        p2=new JPanel();
        p2.add(new JLabel("\tPassword:"));
        passWord=new JTextField(20);
        p2.add(passWord);
 
 
        p3=new JPanel();
 
        login=new JButton("     Login    ");
        register=new JButton("   Register   ");
        p3.add(login);
        p3.add(register);
 
        p4=new JPanel();
        p4.add(new JLabel("PasswordCheck:"));
        passwordCheck=new JTextField(20);
        p4.add(passwordCheck);
 
 
        frame.add(p1);
        frame.add(p2);
        frame.add(p4);//ȷ�������
        frame.add(p3);
 
 
        frame.pack();
        frame.setVisible(true);
        p4.setVisible(false);
        show();
        /*****************************Login****************************/
    }
 
 
 
    public void show(){
 
        login.addActionListener(this);//��Ӽ�����
        register.addActionListener(this);
        frame.setBounds(500,500,350,250);//���ô�С
        frame.setLayout(new FlowLayout());//������ʽ����
    }
 
 
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        if(e.getActionCommand().equals("   Register   ")) {//ע�ᣬ�����������õİ�ť�ı��������Ҳ���ǵ���İ�ť�ı�������Ļ���ִ����������
            if(p4.isVisible()==false)
            {
                p4.setVisible(true);//�������������
                login.setText("     Cancel    ");//���������ı���Ϊ������죬ͬʱҲ�ܴ�����Ϊ�ã�����ļ�����
                regirsterable=true;
                return;
            }
            if(regirsterable==true) {
                if (userName.getText().equals(""))//���userName���ı��ǿ�
                {
                    JOptionPane.showMessageDialog(frame, "�û�������Ϊ��");//����
                    return;
                }
 
 
                for (int i = 0; i < Test.usersList.size(); i++) {
 
                    if (Test.usersList.get(i).id.equals(userName.getText())) {
                        JOptionPane.showMessageDialog(frame, "���û��ѱ�ע��");
                        userName.setText("");//��������
                        passWord.setText("");
                        passwordCheck.setText("");
                        return;//���ͬ������������������������������
                    }
 
                }
                //���ִ�е�����˵���ҵ��û���
                if (passWord.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "���벻��Ϊ�գ�����������");
                    return;
 
                } else {
                    if (passwordCheck.getText().equals(passWord.getText())) {
                        Account registeraccount = new Account(userName.getText(), passWord.getText(), "0");//ʵ�������˺�
                        JOptionPane.showMessageDialog(frame, "ע��ɹ������¼");
                        Test.usersList.add(registeraccount);//����Test��ľ�̬�û�list
                        Test.usersListUpdate();//�����û��ĵ�
 
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "������������벻һ�£�����������");
                        return;
                    }
 
 
                }
            }
 
 
            }
        if(e.getActionCommand().equals("     Login    ")){
            for (int i = 0; i < Test.usersList.size(); i++) {
 
                if (Test.usersList.get(i).id.equals(userName.getText())) {
 
                    if(passWord.getText().equals(Test.usersList.get(i).password))
                    {
                        JOptionPane.showMessageDialog(frame, "��¼�ɹ�");
                        Test.currentAccount=Test.usersList.get(i);//��list�з��ϵ�½������˻����������Ϊ��ǰTest���еľ�̬�ġ���ǰ�ࡱ���Ա������ֲ���;
                        Test.file=new File(Test.currentAccount+".txt");
                        Test.recordString=new StringBuilder();//��գ����⽫��һ���û��ļ�¼д���µ�¼���û���
                        //Test.recordString.append("");//����recordString��ָ��
                        Menu menu=new Menu();//ʵ�����˵�����
 
                        Test.menu=menu;
                        frame.setVisible(false);//���ص�½����
 
                        /************************������¼�ļ�**********************/
                        File records = new File(Test.currentAccount.id+".txt");//���˻�id����
                        if(!records.exists())
                        {
                            try {
                                records.createNewFile();
                            }
                            catch (Exception e1)
                            {
                                JOptionPane.showMessageDialog(null, "�������û��ļ�¼ʧ��");
                            }
                        }
                        Test.file=records;
                        /*****************��ȡ��¼�ļ�************/
                        try {
                             fw = new FileReader(Test.file);//�ַ���
                        }
                        catch (Exception e1)
                        {
                            JOptionPane.showMessageDialog(null, "��ȡ��¼ʧ��");
                        }
                        BufferedReader bfr=new BufferedReader(fw);
 
                        String temp="";
                        try {
 
 
                            while ((temp = bfr.readLine()) != null) {//��֪Ϊ�ζ�ȡ�����ַ�������ǰ������Null������Ӱ��ʹ��
                                Test.recordString .append(temp);//��ȡԭ�ȸ��˻��ļ�¼��ÿһ�в�ƴ�ӵ�Test.recordString�У���inqury���������Ϊ��ѯ��¼�Ľ��
                            }
                            //����¼��ȡ���ϲ�Ϊһ���ַ���
 
 
 
                            fw.close();
                        }
                        catch (Exception e1)
                        {
                            System.out.println("��ȡ��¼�����г��ִ���");
                        }
 
 
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "�������");
                        passwordCheck.setText("");
                        return;
                    }
 
                }
            }
            JOptionPane.showMessageDialog(frame, "���û�������");
 
 
        }
        if(e.getActionCommand().equals("     Cancel    "))
        {
            p4.setVisible(false);
            login.setText("     Login    ");
            regirsterable=false;//����ע��
        }
 
 
    }
}
