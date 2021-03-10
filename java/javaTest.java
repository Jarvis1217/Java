package javaTest;

import java.util.Scanner;

class point
{
	//数据
	private int x;
	private int y;
	//接口
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
		//功能选择
		int s;  //记录操作符
		int n=0;//统计点的个数
		point p[]=null;
		p=new point[1000];
		System.out.print("tips:");
	    System.out.println("0-退出程序;1-新增点;2-显示点坐标;3-输出点的个数");
	    System.out.println();
		do
		{
			System.out.print("请输入操作符:");
			s=reader.nextInt();
			switch (s)
			{
			case 0:
				{
					System.out.print("感谢使用!");
				    break;
				}
            case 1:
				{
					n++;
					System.out.print("请输入点的坐标:");
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
						System.out.println("此时没有点，无法输出");
						System.out.println();
						break;
					}
					for (i=0;i<n;i++)
					{
						int e=p[i].get_x();
						int f=p[i].get_y();
						System.out.println("点的坐标为:"+"("+e+","+f+")");
					}
					System.out.println();
					break;
				}
			case 3:
				{
					System.out.println("此时共有"+n+"个点");
					 System.out.println();
					break;
				}
			}
		}
		while (s!=0);		
	}
}
