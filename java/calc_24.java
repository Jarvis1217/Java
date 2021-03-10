package test;

import java.util.Random;

public class calc_24
{
	public static void main(String[] args)
	{
		Random random=new Random();
		int[] num_list=new int[4];
		int i;

		//生成随机数组
		for(i=0;i<4;i++)
		{
			num_list[i]=random.nextInt(9)+1;
		}
		
		System.out.print("随机数组为:");
		
		//输出随机数组
		for(i=0;i<4;i++)
		{
			System.out.print(num_list[i]+" ");
		}
		System.out.println();
		
		System.out.println("计算结果:");
		print_result(num_list);
	}
	//打印解的个数
	public static void print_result(int[] num)
	{
		String[] symbols={"+","-","*","/"};
		String[] op=new String[3];
		int i,j,k,count=0;
		for(i=0;i<4;i++)
		{
			for(j=0;j<4;j++)
			{
				for(k=0;k<4;k++)
				{
					op[0]=symbols[i];
					op[1]=symbols[j];
					op[2]=symbols[k];
					
					if(main_calc(num,op)==1)
						count+=1;
				}
			}
		}
		
		if(count!=0)
		{
			System.out.println("此数列共有"+count+"种解");
		}
		else
		{
			System.out.println("此数列无解");
		}
	}
	//构建表达式
	public static int main_calc(int[] num,String[] op)
	{
		int a=num[0];
		int b=num[1];
		int c=num[2];
		int d=num[3];
		double t;
		//1、((A*B)*C)*D
		t=0;
		t=get_value(a,b,op[0]);
		t=get_value(t,c,op[1]);
		t=get_value(t,d,op[2]);
		if(Math.abs(t-24)<0.0001)
		{
			print_answer(1,num,op);
			return 1;
		}
		//2、(A*(B*C))*D
		t=0;
		t=get_value(b,c,op[1]);
		t=get_value(t,a,op[0]);
		t=get_value(t,d,op[2]);
		if(Math.abs(t-24)<0.0001)
		{
			print_answer(2,num,op);
			return 1;
		}
		//3、(A*B)*(C*D)
		t=0;
		t=get_value(get_value(a,b,op[0]),get_value(c,d,op[2]),op[1]);
		if(Math.abs(t-24)<0.0001)
		{
			print_answer(3,num,op);
			return 1;
		}
		//4、A*(B*(C*D))
		t=0;
		t=get_value(c,d,op[2]);
		t=get_value(b,t,op[1]);
		t=get_value(a,t,op[0]);
		if(Math.abs(t-24)<0.0001)
		{
			print_answer(4,num,op);
			return 1;
		}
		//5、A*((B*C)*D)
		t=0;
		t=get_value(b,c,op[1]);
		t=get_value(t,d,op[2]);
		t=get_value(a,t,op[0]);
		if(Math.abs(t-24)<0.0001)
		{
			print_answer(5,num,op);
			return 1;
		}
		return 0;
	}
	//计算
	public static double get_value(double num1,double num2,String op)
	{
		double result=0;
		if(op=="+")
		{
			result=num1+num2;
		}
		if(op=="-")
		{
			result=num1-num2;
		}
		if(op=="*")
		{
			result=num1*num2;
		}
		if(op=="/")
		{
			result=num1/num2;
		}
		return result;
	}
	//打印答案
	public static void print_answer(int flag,int[] num,String[] op)
	{
		int a=num[0];
		int b=num[1];
		int c=num[2];
		int d=num[3];
		//1、((A*B)*C)*D
		if(flag==1)
		{
			System.out.println("(("+a+op[0]+b+")"+op[1]+c+")"+op[2]+d);
		}
		//2、(A*(B*C))*D
		if(flag==2)
		{
			System.out.println("("+a+op[0]+"("+b+op[1]+c+"))"+op[2]+d);
		}
		//3、(A*B)*(C*D)
		if(flag==3)
		{
			System.out.println("("+a+op[0]+b+")"+op[1]+"("+c+op[2]+d+")");
		}
		//4、A*(B*(C*D))
		if(flag==4)
		{
			System.out.println(a+op[0]+"("+b+op[1]+"("+c+op[2]+d+"))");
		}
		//5、A*((B*C)*D)
		if(flag==5)
		{
			System.out.println(a+op[0]+"(("+b+op[1]+c+")"+op[2]+d+")");
		}
	}
}