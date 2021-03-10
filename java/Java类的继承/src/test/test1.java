package test;

class Point
{
	//Data
	protected int x;
	protected int y;
	//Function
	Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void show()
	{
		System.out.println("点的坐标为:");
		System.out.println("x="+x);
		System.out.println("y="+y);
	}
}

class Circle extends Point
{
	//Data
	protected int rad;
	//Function
	Circle(int x,int y,int rad)
	{
		super(x,y);
		this.rad=rad;
	}
	public double area()
	{
		return Math.PI*rad*rad;
	}
	public void show()
	{
		System.out.println("圆的信息如下:");
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("area="+area());
	}
}

class Cylinder extends Circle
{
	//Data
	protected int height;
	//Function
	Cylinder(int x,int y,int rad,int height)
	{
		super(x,y,rad);
		this.height=height;
	}
	public double volume()
	{
		return area()*height;
	}
	public double peri()
	{
		return Math.PI*2*rad;
	}
	public double sur_area()
	{
		return area()*2+height*peri();
	}
	public void show()
	{
		System.out.println("圆柱体的信息如下:");
		System.out.println("height="+height);
		System.out.println("area="+area());
		System.out.println("volume="+volume());
		System.out.println("surface area="+sur_area());
	}
}

public class test1 
{
	public static void main(String[] args)
	{
		Point p=new Point(0,0);
		Circle c=new Circle(0,0,2);
		Cylinder cy=new Cylinder(0,0,2,3);
		p.show();
		c.show();
		cy.show();
	}
}
