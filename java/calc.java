package calculate;

class calculate
{
	//数据
	protected int x;
	protected int y;
	//接口
	calculate(int m,int n)
	{
		x=m;
		y=n;
	}
	//加
	public int add()
	{
		return (x+y);
	}
	//减
	public int sub()
	{
		return (x-y);
	}
	//乘
	public int mul()
	{
		return (x*y);
	}
	//除
	public int div()
	{
		return (x/y);
	}
}

public class calc 
{
	public static void main(String[] args) 
	{
		calculate cal=new calculate(10,5);
		System.out.println("相加后结果为:"+cal.add());
		System.out.println("相减后结果为:"+cal.sub());
		System.out.println("相乘后结果为:"+cal.mul());
		System.out.println("相除后结果为:"+cal.div());
	}
}
