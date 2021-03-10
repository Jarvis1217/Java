package BankOS;

//账号为100001~100005
//密码为1231~1235

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//用户类
class users
{
	//数据
	private int Account;
	private String Name;
	private String Address;
	private int Balance;
	private int Password;
	private static int minBalance=10;
	//接口
	public users(int acc,String name,String add,int bal,int pass)
	{
		Account=acc;
		Name=name;
		Address=add;
		Balance=bal;
		Password=pass;
	}
	
	//输出用户信息
	public String[] getArray()
	{
		String[] str=new String[3];
		str[0]=new String(Name);
		str[1]=new String(Address);
		String s = String.valueOf(Balance);
		str[2]=new String(s);
		return str;
	}
	
	//返回用户密码
	public int get_pass()
	{
		return Password;
	}
	//返回最小余额
	public int get_minBala()
	{
		return minBalance;
	}
	//存款
	public void deposit(int mon0)
	{
		Balance=Balance+mon0;
	}
	//取款
		public int withdraw(int mon1)
		{
			int check=Balance-mon1;
			if(check<minBalance)
				return 0;
			else
			{
				Balance=Balance-mon1;
				return 1;
			}
		}
}

//主类
public class BankOS 
{
	public static void main(String[] args)
	{
		//用户信息
		users[] us=new users[5];
		us[0]=new users(100001,"Holmes","Baker Street 221B",8000,1231);
		us[1]=new users(100002,"Kimi","Baker Street 222A",6000,1232);
		us[2]=new users(100003,"Jason","Baker Street 222B",7000,1233);
		us[3]=new users(100004,"Tom","Baker Street 223A",7500,1234);
		us[4]=new users(100005,"Timo","Baker Street 223B",6300,1235);
		//设置登陆窗体信息
		Frame fr0=new Frame("BankOS");
		fr0.setVisible(true);
		fr0.setLocation(400,100);
		fr0.setSize(800,600);
		//设置登陆成功后的窗体信息
		Frame fr1=new Frame("BankOS");
		fr1.setVisible(false);
		fr1.setLocation(400,100);
		fr1.setSize(800,600);
		//设置退出账号后的窗体信息
		Frame fr_exit=new Frame("BankOS");
		fr_exit.setVisible(false);
		fr_exit.setLocation(400,100);
		fr_exit.setSize(800,600);
		//设置查询的窗体信息
		Frame fr_print=new Frame("BankOS");
		fr_print.setVisible(false);
		fr_print.setLocation(400,100);
		fr_print.setSize(800,600);
		//设置存款窗口
		Frame fr_in=new Frame("BankOS");
		fr_in.setVisible(false);
		fr_in.setLocation(400,100);
		fr_in.setSize(800,600);
		//设置取款窗口
		Frame fr_out=new Frame("BankOS");
		fr_out.setVisible(false);
		fr_out.setLocation(400,100);
		fr_out.setSize(800,600);
		//关闭窗口
		fr0.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		fr1.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		fr_exit.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		fr_print.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		fr_in.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		
		fr_out.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
		//0号窗口布局
		fr0.setLayout(null);
		JLabel title=new JLabel("Welcome to BankOS");
		fr0.add(title);
		title.setBounds(300,80,200,220);
		title.setFont(new Font("宋体",Font.BOLD,20));
		//错误类型提示
		JLabel wron0=new JLabel("该用户不存在");
		JLabel wron1=new JLabel("密码输入有误");
		
		//1号窗口布局
		fr1.setLayout(null);
		JLabel tit=new JLabel("功 能 选 择");
		fr1.add(tit);
		tit.setBounds(340,10,200,220);
		tit.setFont(new Font("宋体",Font.BOLD,20));
		
		//exit窗口布局
		fr_exit.setLayout(null);
		JLabel Bye=new JLabel("感 谢 使 用 ！");
		fr_exit.add(Bye);
		Bye.setBounds(330,180,200,220);
		Bye.setFont(new Font("宋体",Font.BOLD,25));
		
		//print窗口布局
		fr_print.setLayout(null);
		JLabel tit1=new JLabel("用 户 信 息");
		fr_print.add(tit1);
		tit1.setBounds(330,50,200,220);
		tit1.setFont(new Font("宋体",Font.BOLD,20));
		
		//in窗口布局
		fr_in.setLayout(null);
		JLabel tit2=new JLabel("存 款");
		fr_in.add(tit2);
		tit2.setBounds(360,50,200,220);
		tit2.setFont(new Font("宋体",Font.BOLD,20));
		JLabel tip1=new JLabel("操作成功");
		
		//out窗口布局
		fr_out.setLayout(null);
		JLabel tit3=new JLabel("取 款");
		fr_out.add(tit3);
		tit3.setBounds(360,50,200,220);
		tit3.setFont(new Font("宋体",Font.BOLD,20));
		JLabel tip2=new JLabel("操作成功");
		JLabel tip3=new JLabel("账户内至少存有"+us[0].get_minBala()+"元");
		
		//0号窗口添加按钮
		JButton b1=new JButton("登 陆");
		JButton b2=new JButton("重 输");
		fr0.add(b1);fr0.add(b2);
		b1.setBounds(300,400,70,40);
		b2.setBounds(400,400,70,40);
		
		//1号窗口添加按钮
		JButton bc1=new JButton("存 款");
		JButton bc2=new JButton("取 款");
		JButton bc3=new JButton("信息查询");
		JButton bc4=new JButton("退出账号");
		fr1.add(bc1);fr1.add(bc2);
		fr1.add(bc3);fr1.add(bc4);
		bc1.setBounds(220,250,150,60);
		bc2.setBounds(430,250,150,60);
		bc3.setBounds(220,350,150,60);
		bc4.setBounds(430,350,150,60);
		
		//print窗口添加按钮
		JButton b_print=new JButton("返 回");
		fr_print.add(b_print);
		b_print.setBounds(360,400,70,40);
		
		//in窗口添加按钮
		JButton b_in=new JButton("返 回");
		fr_in.add(b_in);
		b_in.setBounds(360,400,70,40);
		JButton save=new JButton("确 认");
		fr_in.add(save);
		save.setBounds(360,340,70,40);
		
		//out窗口添加按钮
		JButton b_out=new JButton("返 回");
		fr_out.add(b_out);
		b_out.setBounds(360,400,70,40);
		JButton out=new JButton("确 认");
		fr_out.add(out);
		out.setBounds(360,340,70,40);
		
		//0号窗口设置文本框
		JLabel lab1=new JLabel("账 号:");
		JLabel lab2=new JLabel("密 码:");
		JTextField tex1=new JTextField();
		JPasswordField tex2=new JPasswordField();
		fr0.add(lab1);fr0.add(lab2);
		fr0.add(tex1);fr0.add(tex2);
		tex1.setBounds(300,255,200,25);
		tex2.setBounds(300,295,200,25);
		lab1.setBounds(240,255,200,25);
		lab2.setBounds(240,295,200,25);
		lab1.setFont(new Font("宋体",Font.BOLD,15));
		lab2.setFont(new Font("宋体",Font.BOLD,15));
	    
		//print窗口设置文本框
		JLabel name=new JLabel("姓 名:");
		JLabel address=new JLabel("地 址:");
		JLabel balance=new JLabel("余 额:");
		fr_print.add(name);fr_print.add(address);
		fr_print.add(balance);
		name.setBounds(220,255,200,25);
		address.setBounds(220,285,200,25);
		balance.setBounds(220,315,200,25);
		name.setFont(new Font("宋体",Font.BOLD,20));
		address.setFont(new Font("宋体",Font.BOLD,20));
		balance.setFont(new Font("宋体",Font.BOLD,20));
		JTextField na=new JTextField();
		JTextField add=new JTextField();
		JTextField bala=new JTextField();
		fr_print.add(na);fr_print.add(add);
		fr_print.add(bala);
		na.setBounds(300,255,200,25);
		add.setBounds(300,285,200,25);
		bala.setBounds(300,315,200,25);
		
		//in窗口设置文本框
		JLabel save_in=new JLabel("存款金额:");
		fr_in.add(save_in);
		save_in.setBounds(220,285,200,25);
		JTextField savein=new JTextField();
		fr_in.add(savein);
		savein.setBounds(300,285,200,25);
		
		//out窗口设置文本框
		JLabel catch_out=new JLabel("取款金额:");
		fr_out.add(catch_out);
		catch_out.setBounds(220,285,200,25);
		JTextField catchout=new JTextField();
		fr_out.add(catchout);
		catchout.setBounds(300,285,200,25);
		
		//设置按钮功能
		b1.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						int allow=-1;
						//获取用户输入的账号及密码
						String acc=tex1.getText();
						String pas=tex2.getText();
						int account=Integer.parseInt(acc);
					    int password=Integer.parseInt(pas);
					    //账号密码匹配
						if(account==100001)
						{
							if(password==us[0].get_pass())
							{
								allow=0;
							}
							else
							{
								allow=1;
							}
						}
						
						if(account==100002)
						{
							if(password==us[1].get_pass())
							{
								allow=0;
							}
							else
							{
								allow=1;
							}
						}
						
						if(account==100003)
						{
							if(password==us[2].get_pass())
							{
								allow=0;
							}
							else
							{
								allow=1;
							}
						}
						
						if(account==100004)
						{
							if(password==us[3].get_pass())
							{
								allow=0;
							}
							else
							{
								allow=1;
							}
						}
						
						if(account==100005)
						{
							if(password==us[4].get_pass())
							{
								allow=0;
							}
							else
							{
								allow=1;
							}
						}
						//判断下一步操作
						if(allow==-1)
						{
							fr0.add(wron0);
							wron0.setBounds(340,250,200,220);
							wron0.setVisible(true);
						}
						else if(allow==1)
						{
							fr0.add(wron1);
							wron1.setBounds(340,250,200,220);
							wron1.setVisible(true);
						}
						else if(allow==0)
						{
							fr0.setVisible(false);
							fr1.setVisible(true);
						}
					}
				}
		);
		
		b2.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						tex1.setText("");
						tex2.setText("");
						wron0.setVisible(false);
						wron1.setVisible(false);
					}
				}
		);
		
		b_print.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(true);
						fr_print.setVisible(false);
					}
				}
		);
		
		b_in.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(true);
						fr_in.setVisible(false);
						tip1.setVisible(false);
						savein.setText("");
					}
				}
		);
		
		save.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						String save=savein.getText();
						int num=Integer.parseInt(save);
						String acc=tex1.getText();
						int account=Integer.parseInt(acc);
						if(account==100001)
						{
							us[0].deposit(num);
							fr_in.add(tip1);
							tip1.setBounds(360,50,200,320);
							tip1.setVisible(true);
						}
						if(account==100002)
						{
							us[1].deposit(num);
							fr_in.add(tip1);
							tip1.setBounds(360,50,200,320);
							tip1.setVisible(true);
						}
						if(account==100003)
						{
							us[2].deposit(num);
							fr_in.add(tip1);
							tip1.setBounds(360,50,200,320);
							tip1.setVisible(true);
						}
						if(account==100004)
						{
							us[3].deposit(num); 
							fr_in.add(tip1);
							tip1.setBounds(360,50,200,320);
							tip1.setVisible(true);
						}
						if(account==100005)
						{
							us[4].deposit(num);
							fr_in.add(tip1);
							tip1.setBounds(360,50,200,320);
							tip1.setVisible(true);
						}
					}
				}
		);
		
		b_out.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(true);
						fr_out.setVisible(false);
						tip2.setVisible(false);
						tip3.setVisible(false);
						catchout.setText("");
						catchout.setEditable(true);
					}
				}
		);
		
		out.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						String out=catchout.getText();
						int num=Integer.parseInt(out);
						String acc=tex1.getText();
						int account=Integer.parseInt(acc);
						if(account==100001)
						{
							int z=us[0].withdraw(num);
							if(z==1)
							{
								fr_out.add(tip2);
								tip2.setBounds(360,50,200,320);
								tip2.setVisible(true);
							}
							else
							{
								fr_out.add(tip3);
								tip3.setBounds(340,50,200,280);
								tip3.setVisible(true);
								catchout.setEditable(false);
							}
						}
						if(account==100002)
						{
							int z=us[1].withdraw(num);
							if(z==1)
							{
								fr_out.add(tip2);
								tip2.setBounds(360,50,200,320);
								tip2.setVisible(true);
							}
							else
							{
								fr_out.add(tip3);
								tip3.setBounds(340,50,200,280);
								tip3.setVisible(true);
								catchout.setEditable(false);
							}
						}
						if(account==100003)
						{
							int z=us[2].withdraw(num);
							if(z==1)
							{
								fr_out.add(tip2);
								tip2.setBounds(360,50,200,320);
								tip2.setVisible(true);
							}
							else
							{
								fr_out.add(tip3);
								tip3.setBounds(340,50,200,280);
								tip3.setVisible(true);
								catchout.setEditable(false);
							}
						}
						if(account==100004)
						{
							int z=us[3].withdraw(num);
							if(z==1)
							{
								fr_out.add(tip2);
								tip2.setBounds(360,50,200,320);
								tip2.setVisible(true);
							}
							else
							{
								fr_out.add(tip3);
								tip3.setBounds(340,50,200,280);
								tip3.setVisible(true);
								catchout.setEditable(false);
							}
						}
						if(account==100005)
						{
							int z=us[4].withdraw(num);
							if(z==1)
							{
								fr_out.add(tip2);
								tip2.setBounds(360,50,200,320);
								tip2.setVisible(true);
							}
							else
							{
								fr_out.add(tip3);
								tip3.setBounds(340,50,200,280);
								tip3.setVisible(true);
								catchout.setEditable(false);
							}
						}
					}
				}
		);
		
		bc1.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(false);
						fr_in.setVisible(true);
					}
				}
		);
		
		bc2.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(false);
						fr_out.setVisible(true);
					}
				}
		);
		
		bc3.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(false);
						fr_print.setVisible(true);
						//账号匹配
						String acc=tex1.getText();
						int account=Integer.parseInt(acc);
						if(account==100001)
						{
							String[] a1=us[0].getArray();
							na.setText(String.valueOf(a1[0]));
							add.setText(String.valueOf(a1[1]));
							bala.setText(String.valueOf(a1[2]));
							na.setEditable(false); 
							add.setEditable(false); 
							bala.setEditable(false); 
						}
						if(account==100002)
						{
							String[] a1=us[1].getArray();
							na.setText(String.valueOf(a1[0]));
							add.setText(String.valueOf(a1[1]));
							bala.setText(String.valueOf(a1[2]));
							na.setEditable(false); 
							add.setEditable(false); 
							bala.setEditable(false); 
						}
						if(account==100003)
						{
							String[] a1=us[2].getArray();
							na.setText(String.valueOf(a1[0]));
							add.setText(String.valueOf(a1[1]));
							bala.setText(String.valueOf(a1[2]));
							na.setEditable(false); 
							add.setEditable(false); 
							bala.setEditable(false); 
						}
						if(account==100004)
						{
							String[] a1=us[3].getArray();
							na.setText(String.valueOf(a1[0]));
							add.setText(String.valueOf(a1[1]));
							bala.setText(String.valueOf(a1[2]));
							na.setEditable(false); 
							add.setEditable(false); 
							bala.setEditable(false); 
						}
						if(account==100005)
						{
							String[] a1=us[4].getArray();
							na.setText(String.valueOf(a1[0]));
							add.setText(String.valueOf(a1[1]));
							bala.setText(String.valueOf(a1[2]));
							na.setEditable(false); 
							add.setEditable(false); 
							bala.setEditable(false); 
						}
					}
				}
		);
		
		bc4.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						fr1.setVisible(false);
						fr_exit.setVisible(true);
					}
				}
		);
	}
}
