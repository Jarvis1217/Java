package calculate;

class calculate
{
	//����
	protected int x;
	protected int y;
	//�ӿ�
	calculate(int m,int n)
	{
		x=m;
		y=n;
	}
	//��
	public int add()
	{
		return (x+y);
	}
	//��
	public int sub()
	{
		return (x-y);
	}
	//��
	public int mul()
	{
		return (x*y);
	}
	//��
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
		System.out.println("��Ӻ���Ϊ:"+cal.add());
		System.out.println("�������Ϊ:"+cal.sub());
		System.out.println("��˺���Ϊ:"+cal.mul());
		System.out.println("�������Ϊ:"+cal.div());
	}
}
