package test;

class Wheel
{
	//Data
	protected String Type;
	protected int Amount;
	//Func
	Wheel(String type,int amount)
	{
		Type=type;
		Amount=amount;
	}
	public void getInfo()
	{
		System.out.println("Type:"+Type);
		System.out.println("Amount:"+Amount);
	}
}

class Bus extends Wheel
{
	//Func
	Bus(String type,int amount)
	{
		super(type,amount);
	}
	public void getInfo()
	{
		System.out.println("Type:"+Type);
		System.out.println("Amount:"+Amount);
	}
}

class Bike extends Wheel
{
	Bike(String type,int amount)
	{
		super(type,amount);
	}
	public void getInfo()
	{
		System.out.println("Type:"+Type);
		System.out.println("Amount:"+Amount);
	}
}

public class test3 
{
	public static void main(String[] args)
	{
		Wheel bus=new Bus("公共汽车",25);
		Wheel bike=new Bike("自行车",100);
		bus.getInfo();
		bike.getInfo();
	}
}
