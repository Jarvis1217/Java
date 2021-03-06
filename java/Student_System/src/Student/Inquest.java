package Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Inquest extends JDialog implements ActionListener
{ 
  Hashtable 基本信息表=null;                           
  JTextField 学号,姓名,所学专业,家庭住址,出生;                 
  JRadioButton 男,女;
  JButton 查询;
  ButtonGroup group=null;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  File file=null;                                           
  public Inquest(JFrame f,File file)
  {
   super(f,"查询对话框",false);                           
   this.file=file;
   学号=new JTextField(10);
   查询=new JButton("查询");
   学号.addActionListener(this);
   查询.addActionListener(this);
   姓名=new JTextField(10);
   姓名.setEditable(false);
   所学专业=new JTextField(10);
   所学专业.setEditable(false);
   家庭住址=new JTextField(10);
   家庭住址.setEditable(false);
   出生=new JTextField(10);
   出生.setEditable(false);
   男=new JRadioButton("男",false);
   女=new JRadioButton("女",false);
   group=new ButtonGroup();
   group.add(男);
   group.add(女);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("输入要查询的学号:",JLabel.CENTER));
   box1.add(学号);
   box1.add(查询);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("姓名:",JLabel.CENTER));
   box2.add(姓名);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("性别:",JLabel.CENTER));
   box3.add(男);
   box3.add(女);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("所学专业:",JLabel.CENTER));
   box4.add(所学专业);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("家庭住址:",JLabel.CENTER));
   box5.add(家庭住址);
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("出生:",JLabel.CENTER));
   box6.add(出生);
   Box boxH=Box.createVerticalBox();              
   boxH.add(box1);
   boxH.add(box2);
   boxH.add(box3);
   boxH.add(box4);
   boxH.add(box5);
   boxH.add(box6);
   boxH.add(Box.createVerticalGlue());          
   JPanel pCenter=new JPanel();
   pCenter.add(boxH);
   Container con=getContentPane();
   con.add(pCenter,BorderLayout.CENTER);
   con.validate();
   setVisible(false);
   setBounds(100,200,360,270);
   addWindowListener(new WindowAdapter()
                    { public void windowClosing(WindowEvent e)
                       {
                         setVisible(false);
      	               }
                    });
  }
 public void actionPerformed(ActionEvent e)
  {    
     姓名.setText(null);
     所学专业.setText(null);
     家庭住址.setText(null);
     出生.setText(null); 
    if(e.getSource()==查询||e.getSource()==学号)
      {
         String number="";
         number=学号.getText();
        
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    基本信息表=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(基本信息表.containsKey(number))          
                 {
                   Student stu=(Student)基本信息表.get(number);
                   姓名.setText(stu.getName());
                   所学专业.setText(stu.getDisciping());
                   家庭住址.setText(stu.getGrade());
                   出生.setText(stu.getBorth()); 
                   if(stu.getSex().equals("男"))
                      {
                        男.setSelected(true);
                      }
                    else
                      {
                        女.setSelected(true);
                      }
                 }
              else
                 { 
                  String warning="该学号不存在!";
                  JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
                 }
            }
        else
            { 
              String warning="必须要输入学号!";
              JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
            }
      } 
  }
}