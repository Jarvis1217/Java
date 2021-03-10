import java.util.Scanner;
class Average
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.print("请输入数组长度:");
		int n=reader.nextInt();
		//创建数组
		int i=0;
		int[] a=new int[n];
		System.out.print("请输入数据:");
		for (i=0;i<n;i++)
		{
			a[i]=reader.nextInt();
		}

		//求平均数
		int sum=0;
		for (i=0;i<n;i++)
		{
			sum=sum+a[i];
		}
		int Average=sum/n;
		System.out.print("Average="+Average);
		System.out.println();

		//求最大值
		int max=a[0];
		for (i=1;i<n;i++)
		{
			if(a[i]>max)
				max=a[i];
		}
		System.out.print("max="+max);
		System.out.println();

		//求最小值
		int min=a[0];
		for (i=1;i<n;i++)
		{
			if(a[i]<min)
				min=a[i];
		}
		System.out.print("min="+min);
		System.out.println();
	}
}
