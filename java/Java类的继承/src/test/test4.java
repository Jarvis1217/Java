package test;

class Student
{
	//Data
	protected String Name;
	protected int Age;
	protected String Degree;
	//Func
	Student(String name,int age,String degree)
	{
		Name=name;
		Age=age;
		Degree=degree;
	}
	public void show()
	{
		System.out.println("Name:"+Name);
		System.out.println("Age:"+Age);
		System.out.println("Degree:"+Degree);
	}
}

class Under_graduate extends Student
{
	//Data
	protected String Specialty;
	//Func
	Under_graduate(String name,int age,String degree,String specialty)
	{
		super(name,age,degree);
		Specialty=specialty;
	}
	public void show()
	{
		System.out.println("Name:"+Name);
		System.out.println("Age:"+Age);
		System.out.println("Degree:"+Degree);
		System.out.println("Specialty:"+Specialty);
	}
}

class Graduate extends Student
{
	//Data
	protected String Direction;
	//Func
	Graduate(String name,int age,String degree,String direction)
	{
		super(name,age,degree);
		Direction=direction;
	}
	public void show()
	{
		System.out.println("Name:"+Name);
		System.out.println("Age:"+Age);
		System.out.println("Degree:"+Degree);
		System.out.println("Direction:"+Direction);
	}
}

public class test4 
{
	public static void main(String[] args)
	{
		Student under_gra=new Under_graduate("LiMing",20,"un_graduate","物联网工程");
		Student gradu=new Graduate("LiHua",23,"graduate","物联网开发");
		under_gra.show();
		gradu.show();
	}
}
