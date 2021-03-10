import java.util.Scanner;
class NumberOff 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.print("请输入人数:");
		n=sc.nextInt();
		int[] a=new int[n];
		//创建队列并编号
		int i;
		for (i=0;i<n;i++)
		{
			a[i]=i+1;
		}
		//第一次输出
		System.out.println("当前队列中人数编号:");
		for (i=0;i<n;i++)
		{
			System.out.print(a[i]+" ");
		}
		
		System.out.println();
		
		//更新后输出
		System.out.println("更新后队列中人数编号:");
		for (i=0;i<n;i++)
		{
			if (a[i]%3==0)
				continue;
			else
			System.out.print(a[i]+" ");
		}
	}
}
