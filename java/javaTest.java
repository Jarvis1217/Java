package javaTest;

import java.util.Scanner;

class point
{
	//����
	private int x;
	private int y;
	//�ӿ�
	point(int a,int b)
	{
		x=a;
		y=b;
	}
	public int get_x()
	{
		return x;
	}
	public int get_y()
	{
		return y;
	}
}

public class javaTest 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		//����ѡ��
		int s;  //��¼������
		int n=0;//ͳ�Ƶ�ĸ���
		point p[]=null;
		p=new point[1000];
		System.out.print("tips:");
	    System.out.println("0-�˳�����;1-������;2-��ʾ������;3-�����ĸ���");
	    System.out.println();
		do
		{
			System.out.print("�����������:");
			s=reader.nextInt();
			switch (s)
			{
			case 0:
				{
					System.out.print("��лʹ��!");
				    break;
				}
            case 1:
				{
					n++;
					System.out.print("������������:");
					int a=reader.nextInt();
					int b=reader.nextInt();
					p[n-1]=new point(a,b);
					 System.out.println();
					break;
				}
			case 2:
				{
					int i=0;
					if(n==0)
					{
						System.out.println("��ʱû�е㣬�޷����");
						System.out.println();
						break;
					}
					for (i=0;i<n;i++)
					{
						int e=p[i].get_x();
						int f=p[i].get_y();
						System.out.println("�������Ϊ:"+"("+e+","+f+")");
					}
					System.out.println();
					break;
				}
			case 3:
				{
					System.out.println("��ʱ����"+n+"����");
					 System.out.println();
					break;
				}
			}
		}
		while (s!=0);		
	}
}
