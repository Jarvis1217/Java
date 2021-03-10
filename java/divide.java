import java.util.Scanner;
class divide 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		int n;
		do
		{
			System.out.println("请输入整数(0<n<1000):");
			n=reader.nextInt();
			//容错处理
			if(n<=0||n>=1000)
			{
				System.out.println("输入有误,请重新输入:");
				System.out.println();
				continue;
			}
			//判断
			if(n%9==0&&n!=1)
			{
				System.out.println(n+"能被9整除");
				System.out.println();
			}
			else if(n%9!=0&&n!=1)
			{
				System.out.println(n+"不能被9整除");
				System.out.println();
			}
			//结束
			if(n==1)
			{
				System.out.println("感谢使用!");
				break;
			}
		}while(n!=1);
	}
}
