import java.util.Scanner;
class halfArray 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
        //创建数组
		System.out.println("请输入自定义数组长度:");
		int n=reader.nextInt();
		int i=0;
		int[] a=new int[n];
		System.out.println("请输入数组元素:");
		for(i=0;i<n;i++)
		{
			a[i]=reader.nextInt();
		}
		//输出数组
		System.out.println("自定义数组为:");
		for(i=0;i<n;i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
		//调用halfArray函数
		int[] c=halfArray(a,n);
		//输出新的数组
		System.out.println("新的数组为:");
		for(i=0;i<n;i++)
		{
			System.out.print(c[i]+" ");
		}
		
	}
	//定义halfArray函数
	public static int[] halfArray(int[] a,int n)
	{
		int j=0;
		int[] b=new int[n];
		for(j=0;j<n;j++)
		{
			b[j]=a[j]/2;
		}
		return b;
	}
}
