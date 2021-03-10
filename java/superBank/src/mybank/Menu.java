package mybank;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Menu implements ActionListener{
    public JFrame mframe;
    private JPanel mp0,mp1,mp2,mp3,mp4;//p4��ȷ�����룻���register��ťʯ����
 
 
    private JTextField passWord,passwordCheck;
    private JButton inqury;
    private JButton outmoney;
    private JButton transfer;
    private JButton inmoney;
    private JButton changepassword;
 
 
    public Menu()
    {
        mframe=new JFrame();
        mframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton inqury=new JButton("��ѯ");
        JButton outmoney=new JButton("ȡ��");
        JButton transfer=new JButton("ת��");
        JButton inmoney=new JButton("���");
        JButton changepassword=new JButton("��������");
        JButton exit=new JButton("�˿�");
        mp0=new JPanel();
        mp0.add(new JLabel("ѡ����Ŀ"));
        mframe.add(mp0);
        mp1=new JPanel();
 
        mp1.add(inmoney);
        mp1.add(inqury);
        mp1.add(outmoney);
        mp1.add(transfer);
        mp1.add(changepassword);
        mp1.add(exit);
 
        mp1.setLayout(new GridLayout(3,2,20,20));
        mframe.add(mp1);
        mframe.pack();
        mframe.setVisible(true);
        mframe.setLayout(new FlowLayout());
        mframe.setBounds(500,500,450,300);
        inqury.addActionListener(this);//�󶨼�����
        inmoney.addActionListener(this);
        outmoney.addActionListener(this);
        transfer.addActionListener(this);
        changepassword.addActionListener(this);
        exit.addActionListener(this);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();//cmd��ֵΪ����İ�ť��ֵ
        if(cmd.equals("��ѯ"))
        {
        Inqury inquryGui=new Inqury();
        }
        else if(cmd.equals("ȡ��"))
        {
        OutMoney outMoneyGui=new OutMoney();
        }
        else if(cmd.equals("���"))
        {
        InMoney inMoney=new InMoney();
        }else if(cmd.equals("ת��"))
        {
        Transfer transfer=new Transfer();
        }else if(cmd.equals("��������"))
        {
        ChangePassword changePassword=new ChangePassword();
        }
        else if(cmd.equals("�˿�")){
 
            mframe.setVisible(false);//����
            LoginGui loginGui=new LoginGui();
            JOptionPane.showMessageDialog(null,"��ǵ�ȡ��������п�");
        }
       
    }
}
