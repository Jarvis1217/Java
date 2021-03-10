import java.util.Scanner;

class dian
{
	//数据
	private int x;
	private int y;
	//接口
	dian(int a,int b)
	{
		x=a;
		y=b;
	}
	public int get_x()
	{
		return x;
	}
	public int get_y()
	{
		return y;
	}
}

public class point 
{
	public static void main(String[] args) 
	{
		Scanner reader=new Scanner(System.in);
		System.out.print("请输入点的坐标:");
		int a=reader.nextInt();
		int b=reader.nextInt();
		
		dian di=null;
		di=new dian(a,b);

		int m=di.get_x();
		int n=di.get_y();
		System.out.print("点的坐标为:"+"("+m+","+n+")");
	}
}
