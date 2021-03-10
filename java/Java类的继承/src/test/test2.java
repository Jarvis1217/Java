package test;

class Animal
{
	//Data
	protected String type;
	//Function
	Animal(String type)
	{
		this.type=type;
	}
	public void sound()
	{
		System.out.println("父类方法调用");
	}
}

class Dog extends Animal
{
	//Data
	protected String color;
	//Function
	Dog(String type,String color)
	{
		super(type);
		this.color=color;
	}
	public void sound()
	{
		System.out.print("子类Dog方法调用 ");
		System.out.println("Type:"+type);
	}
}

class Cat extends Animal
{
	//Data
	protected String color;
	//Function
	Cat(String type,String color)
	{
		super(type);
		this.color=color;
	}
	public void sound()
	{
		System.out.print("子类Cat方法调用 ");
		System.out.println("Type:"+type);
	}
}

public class test2 
{
	public static void main(String[] args)
	{
		Animal dog=new Dog("哺乳动物","yellow");
		Animal cat=new Cat("哺乳动物","white");
		dog.sound();
		cat.sound();
	}
}
