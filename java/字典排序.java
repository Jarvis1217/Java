package Test;

import java.util.Arrays;
public class test5 
{
	public static void main(String[] args)
	{
		String[] str_array=new String[] {"nba","abc","cba","zz","qq","haha"};
		int len=str_array.length;
		//输出原字符数组
		System.out.println("原字符数组为:");
		for(int i=0;i<len;i++)
			System.out.print(str_array[i]+",");
		System.out.println("\n");
		
		//方法一字典排序
		System.out.println("Arrays.sort()得到的结果:");
		Arrays.sort(str_array);
		for(int i=0;i<len;i++)
			System.out.print(str_array[i]+",");
		System.out.println("\n");
		
		//方法二字典排序
		System.out.println("冒泡排序得到的结果:");
		//冒泡排序
		for(int i=0;i<len-1;i++)
		{
			for(int j=0;j<len-i-1;j++)
			{
				if(str_array[j].compareTo(str_array[j+1])>0)
				{
					String temp=str_array[j];
					str_array[j]=str_array[j+1];
					str_array[j+1]=temp;
				}
			}
		}
		
		for(int i=0;i<len;i++)
			System.out.print(str_array[i]+",");
	}
}
