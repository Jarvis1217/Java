import java.util.Scanner;
class compare 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.println("�������һ������");
		int a=reader.nextInt();
		System.out.println("������ڶ�������");
		int b=reader.nextInt();

		if(a>b)
		{
			System.out.println("����һ������ֵ:" +a);
		}
		else
		{
			System.out.println("����һ������ֵ:" +b);
		}

		int max;
		max=a>b? a:b;
		System.out.println("������������ֵ:" +max);
	}
}
