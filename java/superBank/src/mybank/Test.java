package mybank;
 
//import javafx.scene.layout.Pane;
 
import javax.swing.*;
 
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
 
public class Test {
    public static List<Account> usersList;
    public static Account currentAccount;//��¼���û�
    public static File file;//��ǰ�û��ļ�¼�ļ�
    public static StringBuilder recordString=new StringBuilder();//��¼���ȡ�ı��еļ�¼��Ȼ���recordStringƴ��
    public static Menu menu;//��̬�Ĳ˵����棬�����ڸ��������رղ˵�����
    public static File usersFile;
    public static StringBuilder usersString=new StringBuilder();
 
 
     static Reader fw;
 
    public static void main(String[] args)throws Exception {
 
        usersList = new ArrayList<Account>();
 
        //System.out.println(usersList);
        /**********************�û��ı�**********************/
        File users = new File("users.txt");
 
        if (!users.exists()) {
            try {
                users.createNewFile();
                Writer fw = new FileWriter("users.txt");
                fw.write("admin  12345   88888");
                fw.flush();
                fw.close();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "�����û��ĵ�ʧ��");
            }
 
        }
        usersFile = users;//�����û��ĵ����洢�û��˻������룬�����Ϣ;
        usersListRead();
        usersListUpdate();
        /*****************************Login****************************/
 
        LoginGui loginGui = new LoginGui();
    }
    public static void usersListRead()
    {
        /**********************�����û��ĵ���ȡ�û��б����������û�**********************/
        /**********************��д��list**********************/
        try {
            fw = new FileReader("users.txt");//�ַ���
        } catch (Exception e) {
            System.out.println("�ַ�������ʧ��");
        }
 
        BufferedReader bfr = new BufferedReader(fw);
 
        String temp = "";
        try {
 
            System.out.println("��ʼд��list");
            while ((temp = bfr.readLine()) != null) {//��֪Ϊ�ζ�ȡ�����ַ�������ǰ������Null
                String[] tmpstr = new String[5];
                tmpstr = temp.split("\\s+");//�ָ�ո�
 
                System.out.println("��" + tmpstr[2]);
 
                Account a = new Account(tmpstr[0], tmpstr[1], tmpstr[2]);
                usersList.add(a);
                System.out.println("��ȡ��"+a.id+",ʵ�����û�" + a.id);
 
            }
            bfr.close();
            fw.close();
            System.out.println("�û�list:"+usersList);
        } catch (Exception e) {
            System.out.println("��ȡ�û��ĵ�ʧ��");
        }
    }
 
 
 
 
 
    public static void usersListUpdate()
    {
 
 
 
        /**********************����list����д���ı��û���Ϣ**********************/
        try {
        Writer fw = new FileWriter("users.txt");
 
        StringBuilder tmpstr = new StringBuilder();
        for (int i = 0; i < usersList.size(); i++) {
           // System.out.println(Test.currentAccount.id);
            tmpstr.append(usersList.get(i).id + "    " + usersList.get(i).password + "    " + usersList.get(i).money + "\r\n");
 
            //fw.write(Test.currentAccount.id + "    " + Test.currentAccount.password + "    " + Test.currentAccount.money+"\r\n");
        }
        fw.write(tmpstr.toString());
        fw.flush();
        fw.close();
    }
    catch (Exception e)
    {
        e.printStackTrace();
        System.out.println("�����û�ʧ��");
    }
 
    }
}
