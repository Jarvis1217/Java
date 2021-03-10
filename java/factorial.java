import java.util.Scanner;
class factorial 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.print("请输入数值:");
		int n=reader.nextInt();
		int z=fac(n);
		System.out.println(n+"的阶乘是"+z);
	}
	//递归函数
	public static int fac(int n)
	{
		if(n == 0 || n == 1) return 1;
        return n*fac(n-1);
	}
}