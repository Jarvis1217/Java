package Bank;

import java.util.Scanner;
class users
{
	//����
	private int Account;
	private String Name;
	private String Address;
	private int Balance;
	private int Password;
	private static int minBalance=10;
	//�ӿ�
	public users(int acc,String name,String add,int bal,int pass)
	{
		Account=acc;
		Name=name;
		Address=add;
		Balance=bal;
		Password=pass;
	}
	public void out()
	{
		System.out.println("�û���Ϣ:");
		System.out.println("����:"+Name);
		System.out.println("��ַ:"+Address);
		System.out.println("���:"+Balance);
		System.out.println("------------------------------");
	}
	public int get_pass()
	{
		return Password;
	}
	//���
	public void deposit(int mon0)
	{
		Balance=Balance+mon0;
		System.out.println("���ɹ�");
		System.out.println("------------------------------");
	}
	//ȡ��
		public void withdraw(int mon1)
		{
			int check=Balance-mon1;
			if(check<minBalance)
				System.out.println("�˻������������"+minBalance+"Ԫ");
			else
				Balance=Balance-mon1;
			System.out.println("------------------------------");
		}
}

public class Bank 
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		//�û���Ϣ
		users[] us=new users[5];
		us[0]=new users(100001,"Holmes","Baker Street 221B",8000,1231);
		us[1]=new users(100002,"Kimi","Baker Street 222A",6000,1232);
		us[2]=new users(100003,"Jason","Baker Street 222B",7000,1233);
		us[3]=new users(100004,"Tom","Baker Street 223A",7500,1234);
		us[4]=new users(100005,"Timo","Baker Street 223B",6300,1235);
		//��ѯ����
		int num,i=-1,f=0;
		int password;
		while(i==-1)
		{
			System.out.print("�������˺�:");
			num=sc.nextInt();
			if(num==100001)
			{
				i=0;
				break;
			}
			if(num==100002)
			{
				i=1;
				break;
			}
			if(num==100003)
			{
				i=2;
				break;
			}
			if(num==100004)
			{
				i=3;
				break;
			}
			if(num==100005)
			{
				i=4;
				break;
			}
			else System.out.println("���û�������");
		}
		System.out.print("����������:");
		do
		{
			password=sc.nextInt();
			if(password!=us[i].get_pass())
			{
				System.out.print("�������,����������:");
			}
			else
			{
				System.out.println("------------------------------");
				System.out.println("������ʾ:a-���;b-ȡ��;c-��ѯ;e-�˳�");
				System.out.println("------------------------------");
				f=1;
			}
		}while(f!=1);
		char s;
		int g=1;
		int mon0,mon1;
		do
		{
			System.out.print("�����������:");
			s=sc.next().charAt(0);
			switch(s)
			{
				case 'a':
				{
					System.out.print("����������:");
					mon0=sc.nextInt();
					us[i].deposit(mon0);
					break;
				}
				case 'b':
				{
					System.out.print("������ȡ����:");
					mon1=sc.nextInt();
					us[i].withdraw(mon1);
					break;
				}
				case 'c':
				{
					System.out.println("------------------------------");
					us[i].out();
					break;
				}
				case 'e':
				{
					System.out.println("------------------------------");
					System.out.println("��лʹ��!");
					g=0;
					break;
				}
			}
		}while(g!=0);
	}
}
