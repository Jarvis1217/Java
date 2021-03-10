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
        Font fn = new Font("����",Font.BOLD,15);//�������壬���ù��췽����ʼ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���崰�ڿɹر�
        setBounds(100, 100, 625, 328);//���ڴ�С��λ��
        getContentPane().setLayout(null);//���Բ���

        JDesktopPane desktopPane = new JDesktopPane();//����С����
        //desktopPane.setToolTipText("��������������س�");
        desktopPane.setBounds(24, 12, 171, 286);//�����ı����������
        getContentPane().add(desktopPane);//��ӽ���

        JLabel lblNewLabel = new JLabel("������������������س�");//Ϊ�����С���ڶ����ǩ����
        lblNewLabel.setBounds(0, 12, 171, 13);
        desktopPane.add(lblNewLabel);

        textField = new JTextField();//�ı���
        textField.setBounds(10, 37, 149, 26);
        desktopPane.add(textField);
        textField.setColumns(30);

        List list = new List();//�б��壬���ڴ洢����
        desktopPane.setLayer(list, 100);
        list.setMultipleSelections(true);
        list.setBounds(8, 69, 151, 169);
        desktopPane.add(list);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBounds(216, 12, 317, 286);
        getContentPane().add(desktopPane_1);

        JLabel lblNewLabel_1 = new JLabel("��ȡ���ڳ�Ա");
        lblNewLabel_1.setBounds(12, 12, 220, 19);
        desktopPane_1.add(lblNewLabel_1);

        JLabel label = new JLabel("���γ�ȡ�Ĺ��ڳ�ԱΪ");
        label.setBounds(12, 32, 275, 27);
        desktopPane_1.add(label);

        JTextArea textArea = new JTextArea(3,20);
        textArea.setBounds(12, 82, 281, 192);
        desktopPane_1.add(textArea);
        textArea.setFont(fn);

        JButton btnNewButton = new JButton("��ȡ");
        btnNewButton.setBounds(543, 218, 70, 23);
        getContentPane().add(btnNewButton);

        int i=0;
        ArrayList<String> str = new ArrayList<String>(); 
        textField.addKeyListener(new KeyListener() {//�ı�����̼���
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {//�����ֻس�����ʱ�䣬�ᴦ���ı�����ַ����������ǽ��д��棬��ӵ��б�
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

        btnNewButton.addActionListener(new ActionListener() {//��ť���������������ɵı�����ַ��������е�����Ӧ�±������
            public void actionPerformed(ActionEvent e) {
                // TODO �Զ����ɵķ������
                int n = str.size();
                int x = (int) (Math.random()*n);
                String s0 = str.get(x);
                String s1 = "\t\t"+s0+"\n��ϲ"+s0+"��Ϊ�������˹��ڡ�";
                textArea.setText(s1);
            }
        });
   }
}