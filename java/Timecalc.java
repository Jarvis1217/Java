package Timecalc;

import java.util.Scanner;
class MyTime
{
	//���ݶ���
	private int hour;
	private int minute;
	private int second;
	//�ӿ�
	MyTime(int h,int m,int s)
	{
		hour=h;
		minute=m;
		second=s;
	}
	//���ʱ��
	void print()
	{
		if(hour<10)
		{
			System.out.print("0"+hour+":");
		}
		else
			System.out.print(hour+":");
		
		if(minute<10)
		{
			System.out.print("0"+minute+":");
		}
		else
			System.out.print(minute+":");
		
		if(second<10)
		{
			System.out.println("0"+second);
		}
		else
			System.out.println(second);
	}
	//second�ӷ�
	void addSecond(int sec)
	{
		second=second+sec;
		if(second==60)
		{
			minute=minute+1;
			second=0;
		}
		if(minute==60)
		{
			hour=hour+1;
			minute=0;
		}
		if(hour==24)
		{
			hour=0;
		}
	}
	//minute�ӷ�
	void addMinute(int min)
	{
		minute=minute+min;
		if(minute==60)
		{
			hour=hour+1;
			minute=0;
		}
		if(hour==24)
		{
			hour=0;
		}
	}
	//hour�ӷ�
	void addHour(int hou)
	{
		hour=hour+hou;
		if(hour==24)
		{
			hour=0;
		}
	}
	//second����
	void subSecond(int sec)
	{
		if(second<sec)
		{
			if(minute<=0)
			{
				minute=59;
				hour=hour-1;
			}
			else
				minute=minute-1;
			if(hour<=0)
				hour=23;
			second=60-(sec-second);
		}
		else
			second=second-1;
	}
	//minute����
	void subMinute(int min)
	{
		if(minute<min)
		{
			if(hour==0)
				hour=23;
			else 
				hour=hour-1;
			minute=60-(min-minute);
		}
		else
			minute=minute-1;
	}
	//hour����
	void subHour(int hou)
	{
		hour=hour-1;
		if(hour<=0)
			hour=23;
	}
}

public class Timecalc 
{
	public static void main(String[] args)
	{
		int h,m,s;
		int sec,min,hou;
		Scanner sc=new Scanner(System.in);
		//������ʼʱ��
		System.out.print("�������ʼʱ��:");
		h=sc.nextInt();
		m=sc.nextInt();
		s=sc.nextInt();
		MyTime mytime=new MyTime(h,m,s);
		
		//���ʱ��
		System.out.print("��ǰʱ��Ϊ:");
		mytime.print();
		
		//ʱ�����
		System.out.println();
		System.out.print("������Ӽ�������:");
		char ch = sc.next().charAt(0);
		System.out.print("�밴˳�����������ʱ����:");
		hou=sc.nextInt();
		min=sc.nextInt();
		sec=sc.nextInt();
		System.out.println();
		switch(ch)
		{
			case '+':
			{
				mytime.addHour(hou);
				mytime.addMinute(min);
				mytime.addSecond(sec);
				System.out.print("�����ʱ��Ϊ:");
				mytime.print();
				break;
			}
			case '-':
			{
				mytime.subHour(hou);
				mytime.subMinute(min);
				mytime.subSecond(sec);
				System.out.print("�����ʱ��Ϊ:");
				mytime.print();
				break;
			}
		}
	}
}
