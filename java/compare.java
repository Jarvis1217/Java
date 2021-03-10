import java.util.Scanner;
class compare 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.println("请输入第一个整数");
		int a=reader.nextInt();
		System.out.println("请输入第二个整数");
		int b=reader.nextInt();

		if(a>b)
		{
			System.out.println("方法一输出最大值:" +a);
		}
		else
		{
			System.out.println("方法一输出最大值:" +b);
		}

		int max;
		max=a>b? a:b;
		System.out.println("方法二输出最大值:" +max);
	}
}
