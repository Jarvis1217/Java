package test;

class Employee
{
	//Data
	protected String Name;
	protected int Age;
	protected int Salary;
	Manager mager=null;
	//Func
	Employee(String name,int age,int salary)
	{
		Name=name;
		Age=age;
		Salary=salary;
	}
	public void show()
	{
		System.out.println("Name:"+Name);
		System.out.println("Age:"+Age);
		System.out.println("Salary:"+Salary);
		System.out.println("Manager:"+mager);
	}
}

class Manager extends Employee
{
	//Data
	protected int Manage_Num;
	//Func
	Manager(String name,int age,int salary,int num)
	{
		super(name,age,salary);
		Manage_Num=num;
	}
	public void show()
	{
		System.out.println("Name:"+Name);
		System.out.println("Age:"+Age);
		System.out.println("Salary:"+Salary);
		System.out.println("Manage_num:"+Manage_Num);
	}
}

public class test5 
{
	public static void main(String[] args)
	{
		Employee e=new Employee("Eddie",28,7000);
		Manager m=new Manager("Jams",30,8500,15);
		e.mager=m;
		e.show();
		m.show();
	}
}
