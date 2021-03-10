package Timecalc;

import java.util.Scanner;
class MyTime
{
	//数据定义
	private int hour;
	private int minute;
	private int second;
	//接口
	MyTime(int h,int m,int s)
	{
		hour=h;
		minute=m;
		second=s;
	}
	//输出时间
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
	//second加法
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
	//minute加法
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
	//hour加法
	void addHour(int hou)
	{
		hour=hour+hou;
		if(hour==24)
		{
			hour=0;
		}
	}
	//second减法
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
	//minute减法
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
	//hour减法
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
		//输入起始时间
		System.out.print("请输入初始时间:");
		h=sc.nextInt();
		m=sc.nextInt();
		s=sc.nextInt();
		MyTime mytime=new MyTime(h,m,s);
		
		//输出时间
		System.out.print("当前时间为:");
		mytime.print();
		
		//时间计算
		System.out.println();
		System.out.print("请输入加减操作符:");
		char ch = sc.next().charAt(0);
		System.out.print("请按顺序输入待计算时分秒:");
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
				System.out.print("计算后时间为:");
				mytime.print();
				break;
			}
			case '-':
			{
				mytime.subHour(hou);
				mytime.subMinute(min);
				mytime.subSecond(sec);
				System.out.print("计算后时间为:");
				mytime.print();
				break;
			}
		}
	}
}
